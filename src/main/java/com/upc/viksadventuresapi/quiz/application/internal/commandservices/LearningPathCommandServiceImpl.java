package com.upc.viksadventuresapi.quiz.application.internal.commandservices;

import com.upc.viksadventuresapi.profile.domain.model.events.LearningPathCalculatedEvent;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Response;
import com.upc.viksadventuresapi.quiz.domain.services.LearningPathCommandService;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LearningPathCommandServiceImpl implements LearningPathCommandService {
    private final ResponseRepository responseRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final String modelUrl;

    @Autowired
    public LearningPathCommandServiceImpl(ResponseRepository responseRepository,
                                          ApplicationEventPublisher eventPublisher,
                                          @Value("${model.api.url}") String modelUrl) {
        this.responseRepository = responseRepository;
        this.eventPublisher = eventPublisher;
        this.modelUrl = modelUrl;
    }

    @Override
    public List<Integer> getLearningPath(Long profileId, Long quizId) {
        // Obtener las respuestas desde la base de datos
        List<Response> responses = responseRepository.findResponsesByProfileIdAndQuizId(profileId, quizId);

        // Ordenar las respuestas por puntuación (correctas primero)
        List<Response> sortedResponses = sortAnswersByScore(responses);

        // Extraer las puntuaciones
        List<Integer> answerScores = sortedResponses.stream()
                .map(response -> response.isOptionCorrect() ? 1 : 0)
                .collect(Collectors.toList());

        // Obtener el learningPath del modelo, ahora pasando competenciaId
        List<Integer> rawLearningPath = requestLearningPathFromModel(profileId, answerScores, quizId);

        // Publicar el evento de cálculo de ruta de aprendizaje
        publishLearningPathCalculatedEvent(profileId, quizId, answerScores, rawLearningPath);
        return rawLearningPath;
    }

    private List<Response> sortAnswersByScore(List<Response> responses) {
        return responses.stream()
                .sorted(Comparator.comparingInt((Response r) -> r.isOptionCorrect() ? 1 : 0).reversed())
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<Integer> requestLearningPathFromModel(Long profileId, List<Integer> answerScores, Long quizId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id_estudiante", profileId);
        requestBody.put("preguntas", answerScores);

        // Convertir quizId a String con prefijo "C"
        String competenciaId = "C" + quizId;
        requestBody.put("competencia", competenciaId);

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.postForEntity(
                modelUrl,
                requestBody,
                (Class<Map<String, Object>>) (Class<?>) Map.class
        );

        Map<String, Object> responseBody = responseEntity.getBody();

        if (responseBody != null && responseBody.containsKey("ruta")) {
            try {
                return (List<Integer>) responseBody.get("ruta");
            } catch (ClassCastException e) {
                throw new RuntimeException("Unexpected format for 'ruta' in response", e);
            }
        } else {
            throw new RuntimeException("Failed to retrieve learning path from external service.");
        }
    }

    private void publishLearningPathCalculatedEvent(Long profileId, Long quizId,
                                                    List<Integer> answerScores, List<Integer> rawLearningPath) {
        LearningPathCalculatedEvent event = new LearningPathCalculatedEvent(
                this, profileId, quizId, answerScores, rawLearningPath
        );
        eventPublisher.publishEvent(event);
    }
}