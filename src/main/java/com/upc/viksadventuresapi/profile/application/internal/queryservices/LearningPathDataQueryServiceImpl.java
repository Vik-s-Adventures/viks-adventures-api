package com.upc.viksadventuresapi.profile.application.internal.queryservices;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.LearningPathData;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetAllLearningPathData;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByIdQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByProfileIdQuery;
import com.upc.viksadventuresapi.profile.domain.model.queries.GetLearningPathDataByQuizIdQuery;
import com.upc.viksadventuresapi.profile.domain.services.LearningPathDataQueryService;
import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.LearningPathDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningPathDataQueryServiceImpl implements LearningPathDataQueryService {
    private final LearningPathDataRepository learningPathDataRepository;

    public LearningPathDataQueryServiceImpl(LearningPathDataRepository learningPathDataRepository) {
        this.learningPathDataRepository = learningPathDataRepository;
    }

    @Override
    public Optional<LearningPathData> handle(GetLearningPathDataByIdQuery query) {
        return learningPathDataRepository.findById(query.learningPathDataId());
    }

    @Override
    public List<LearningPathData> handle(GetAllLearningPathData query) {
        return learningPathDataRepository.findAll();
    }

    @Override
    public List<LearningPathData> handle(GetLearningPathDataByProfileIdQuery query) {
        return learningPathDataRepository.findByProfileId(query.profileId());
    }

    @Override
    public List<LearningPathData> handle(GetLearningPathDataByQuizIdQuery query) {
        return learningPathDataRepository.findByQuizId(query.quizId());
    }
}
