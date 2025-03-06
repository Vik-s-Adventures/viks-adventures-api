package com.upc.viksadventuresapi.quiz.domain.model.events;

import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Response;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Result;
import com.upc.viksadventuresapi.quiz.domain.model.commands.CreateOrUpdateResultCommand;
import com.upc.viksadventuresapi.quiz.domain.services.ResultCommandService;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResponseRepository;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResultRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResponseEventListener {

    private final ResultCommandService resultCommandService;
    private final ResponseRepository responseRepository;
    private final ResultRepository resultRepository;

    public ResponseEventListener(ResultCommandService resultCommandService, ResponseRepository responseRepository, ResultRepository resultRepository) {
        this.resultCommandService = resultCommandService;
        this.responseRepository = responseRepository;
        this.resultRepository = resultRepository;
    }

    @EventListener
    public void handleResponseCreated(ResponseCreatedEvent event) {
        Response response = event.getResponse();
        Long profileId = response.getProfileId();
        Long quizId = response.getQuizId();

        // Obtener la cantidad de respuestas registradas por el estudiante en este quiz
        long responseCount = responseRepository.countResponsesByProfileIdAndQuizId(profileId, quizId);

        if (responseCount >= 10) {
            throw new IllegalStateException("El estudiante ya ha alcanzado el límite de 10 respuestas para este quiz.");
        }

        // Buscar si ya existe un Result registrado para este quiz y perfil
        Optional<Result> existingResult = resultRepository.findResultByProfileIdAndQuizId(profileId, quizId);

        // Calcular el nuevo score
        int newScore = getNewScore(existingResult, response);

        // Llamar al servicio de aplicación para manejar la actualización/creación del Result
        CreateOrUpdateResultCommand command = new CreateOrUpdateResultCommand(profileId, quizId, newScore);
        resultCommandService.handle(command);
    }

    private static int getNewScore(Optional<Result> existingResult, Response response) {
        // Si la respuesta es incorrecta, el score se mantiene igual
        if (!response.isOptionCorrect()) {
            return existingResult.map(Result::getScore).orElse(0);
        }

        // Si existe un Result, aumentar el score, pero sin superar 10
        return existingResult.map(result -> Math.min(result.getScore() + 1, 10)).orElse(1);
    }
}
