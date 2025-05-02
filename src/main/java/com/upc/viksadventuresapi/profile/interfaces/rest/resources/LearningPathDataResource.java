package com.upc.viksadventuresapi.profile.interfaces.rest.resources;

import java.util.List;

public record LearningPathDataResource(
        Long id,
        Long profileId,
        Long quizId,
        List<Integer> answers,
        List<Integer> learningPath
) {
}