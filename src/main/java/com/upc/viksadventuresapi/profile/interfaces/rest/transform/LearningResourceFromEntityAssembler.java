package com.upc.viksadventuresapi.profile.interfaces.rest.transform;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.LearningPathData;
import com.upc.viksadventuresapi.profile.interfaces.rest.resources.LearningPathDataResource;

public class LearningResourceFromEntityAssembler {
    public static LearningPathDataResource toResourceFromEntity(LearningPathData learning) {
        return new LearningPathDataResource(
                learning.getId(),
                learning.getProfile().getId(),
                learning.getQuiz().getId(),
                learning.getAnswers(),
                learning.getLearningPath()
        );
    }
}
