package com.upc.viksadventuresapi.profile.domain.services;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.LearningPathData;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetAllLearningPathData;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByIdQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByProfileIdQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByQuizIdQuery;

import java.util.List;
import java.util.Optional;

public interface LearningPathDataQueryService {
    Optional<LearningPathData> handle(GetLearningPathDataByIdQuery query);
    List<LearningPathData> handle(GetAllLearningPathData query);
    List<LearningPathData> handle(GetLearningPathDataByProfileIdQuery query);
    List<LearningPathData> handle(GetLearningPathDataByQuizIdQuery query);
}
