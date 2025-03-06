package com.upc.viksadventuresapi.quiz.application.internal.commandservices;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Quiz;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Response;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Result;
import com.upc.viksadventuresapi.quiz.domain.model.commands.CreateOrUpdateResultCommand;
import com.upc.viksadventuresapi.quiz.domain.model.commands.DeleteResultByIdCommand;
import com.upc.viksadventuresapi.quiz.domain.model.valueobjects.Score;
import com.upc.viksadventuresapi.quiz.domain.services.ResultCommandService;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.QuizRepository;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResponseRepository;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultCommandServiceImpl implements ResultCommandService {
    private final ResultRepository  resultRepository;
    private final QuizRepository quizRepository;
    private final ProfileRepository profileRepository;
    private final ResponseRepository responseRepository;

    public ResultCommandServiceImpl(ResultRepository resultRepository, QuizRepository quizRepository, ProfileRepository profileRepository, ResponseRepository responseRepository) {
        this.resultRepository = resultRepository;
        this.quizRepository = quizRepository;
        this.profileRepository = profileRepository;
        this.responseRepository = responseRepository;
    }

    @Override
    public Optional<Result> handle(CreateOrUpdateResultCommand command) {
        Long profileId = command.profileId();
        Long quizId = command.quizId();

        // Retrieve Quiz and Profile
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz with ID " + quizId + " not found."));
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile with ID " + profileId + " not found."));

        // Retrieve all responses for this student and quiz
        List<Response> responses = responseRepository.findResponsesByProfileIdAndQuizId(profileId, quizId);

        // Calculate the new score (count correct answers)
        int newScore = (int) responses.stream()
                .filter(Response::isOptionCorrect)
                .count();

        // Check if Result exists
        Optional<Result> existingResult = resultRepository.findResultByProfileIdAndQuizId(profileId, quizId);

        Result result;
        if (existingResult.isPresent()) {
            // Update the Result with the recalculated score
            result = existingResult.get();
            resultRepository.delete(result); // Ensure the entity is properly replaced
        }

        // Create a new Result with the updated score
        result = new Result(profile, quiz, new Score(newScore));

        // Save Result
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
}
