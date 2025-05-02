package com.upc.viksadventuresapi.profile.domain.model.events;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.LearningPathData;
import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.LearningPathDataRepository;
import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Quiz;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LearningPathEventListener {

    private final LearningPathDataRepository learningPathDataRepository;
    private final ProfileRepository profileRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public LearningPathEventListener(LearningPathDataRepository learningPathDataRepository,
                                     ProfileRepository profileRepository,
                                     QuizRepository quizRepository) {
        this.learningPathDataRepository = learningPathDataRepository;
        this.profileRepository = profileRepository;
        this.quizRepository = quizRepository;
    }

    @EventListener
    public void onLearningPathCalculated(LearningPathCalculatedEvent event) {
        // Obtener las entidades completas
        Profile profile = profileRepository.findById(event.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + event.getProfileId()));

        Quiz quiz = quizRepository.findById(event.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + event.getQuizId()));

        // Buscar si ya existe un registro con ese profile y quiz
        Optional<LearningPathData> optionalData = learningPathDataRepository
                .findByProfileIdAndQuizId(event.getProfileId(), event.getQuizId());

        LearningPathData learningPathData = optionalData.orElseGet(LearningPathData::new);
        learningPathData.setProfile(profile);
        learningPathData.setQuiz(quiz);
        learningPathData.setAnswers(event.getAnswers());
        learningPathData.setLearningPath(event.getLearningPath());

        learningPathDataRepository.save(learningPathData);
    }
}
