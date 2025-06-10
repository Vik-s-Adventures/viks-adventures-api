package com.upc.viksadventuresapi.quiz.interfaces.rest.transform;

import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Question;
import com.upc.viksadventuresapi.quiz.interfaces.rest.resources.QuestionResource;

public class QuestionResourceFromEntityAssembler {
    public static QuestionResource toResourceFromEntity(Question entity) {
        return new QuestionResource(
                entity.getId(),
                entity.getQuiz().getId(),
                entity.getPerformance().value(),
                entity.getPerformance().description(),
                entity.getQuestionText().questionText(),
                entity.getImageUrl().imageUrl()
        );
    }
}
