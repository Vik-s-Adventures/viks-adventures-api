package com.upc.viksadventuresapi.quiz.application.internal.commandservices;

import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Response;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Option;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Result;
import com.upc.viksadventuresapi.quiz.domain.model.commands.CreateResultCommand;
import com.upc.viksadventuresapi.quiz.domain.model.commands.DeleteResultByIdCommand;
import com.upc.viksadventuresapi.quiz.domain.model.commands.UpdateResultCommand;
import com.upc.viksadventuresapi.quiz.domain.model.queries.GetOptionsByCorrectAndQuizIdQuery;
import com.upc.viksadventuresapi.quiz.domain.model.valueobjects.Score;
import com.upc.viksadventuresapi.quiz.domain.services.OptionQueryService;
import com.upc.viksadventuresapi.quiz.domain.services.ResultCommandService;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.QuizRepository;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResponseRepository;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultCommandServiceImpl implements ResultCommandService {
    private final ResultRepository resultRepository;
    private final QuizRepository quizRepository;
    private final ProfileRepository profileRepository;
    private final ResponseRepository responseRepository;
    private final OptionQueryService optionQueryService;

    @Override
    public Optional<Result> handle(CreateResultCommand command) {
        Long profileId = command.profileId();
        Long quizId = command.quizId();

        // Crear un nuevo Result
        int points = calculateScore(profileId, quizId);

        // Crear y guardar el resultado
        Result result = new Result(profileRepository.findById(profileId).orElseThrow(() -> new IllegalArgumentException("Profile not found")),
                quizRepository.findById(quizId).orElseThrow(() -> new IllegalArgumentException("Quiz not found")),
                new Score(points));

        resultRepository.save(result);
        return Optional.of(result);
    }

    @Override
    public Optional<Result> handle(UpdateResultCommand command) {
        Long profileId = command.profileId();
        Long quizId = command.quizId();

        // Verificar que el Result realmente existe en la base de datos
        Optional<Result> existingResult = resultRepository.findResultByProfileIdAndQuizId(profileId, quizId);
        if (existingResult.isEmpty()) {
            // Añadir log para verificar si el ID existe
            System.out.println("No se encontró el Result con profileId: " + profileId + " y quizId: " + quizId);
            throw new IllegalArgumentException("Result does not exist to update.");
        }

        // Continuar con la lógica de actualización si el Result existe
        Result result = existingResult.get();
        int newScore = calculateScore(profileId, quizId);

        result.setScore(new Score(newScore));
        resultRepository.save(result);
        return Optional.of(result);
    }

    @Override
    public void handle(DeleteResultByIdCommand command) {
        if (!resultRepository.existsById(command.resultId())) {
            throw new IllegalArgumentException("Result with ID " + command.resultId() + " does not exist.");
        }
        try {
            resultRepository.deleteById(command.resultId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting result with ID " + command.resultId());
        }
    }

    //  Calcular el puntaje total del estudiante
    private int calculateScore(Long profileId, Long quizId) {
        // Obtener todas las respuestas del estudiante
        List<Response> responses = responseRepository.findResponsesByProfileIdAndQuizId(profileId, quizId);

        // Obtener las opciones correctas del quiz
        List<Option> correctOptions = optionQueryService.handle(new GetOptionsByCorrectAndQuizIdQuery(quizId));

        // Contar cuántas respuestas del estudiante son correctas
        int correctAnswersCount = (int) responses.stream()
                .map(Response::getOptionId)
                .filter(responseOptionId -> correctOptions.stream()
                        .map(Option::getId)
                        .anyMatch(responseOptionId::equals))
                .count();

        // Calcular el puntaje total (máximo 100)
        return Math.min(correctAnswersCount * 10, 100);
    }
}