package com.upc.viksadventuresapi.quiz.domain.model.events;

import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Response;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Result;
import com.upc.viksadventuresapi.quiz.domain.model.commands.CreateResultCommand;
import com.upc.viksadventuresapi.quiz.domain.model.commands.UpdateResultCommand;
import com.upc.viksadventuresapi.quiz.domain.services.ResultCommandService;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResponseRepository;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResultRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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

        // Contar respuestas previas
        long responseCount = responseRepository.countResponsesByProfileIdAndQuizId(profileId, quizId);
        if (responseCount > 10) {
            throw new IllegalStateException("El estudiante ya ha alcanzado el límite de 10 respuestas para este quiz.");
        }

        // Buscar si ya existe un Result
        Optional<Result> existingResult = resultRepository.findResultByProfileIdAndQuizId(profileId, quizId);

        if (existingResult.isPresent()) {
            // Actualizar el result existente recalcando el puntaje
            resultCommandService.handle(new UpdateResultCommand(profileId, quizId));
        } else {
            // Crear un nuevo result
            resultCommandService.handle(new CreateResultCommand(profileId, quizId));
        }
    }

}