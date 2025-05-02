package com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.LearningPathData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LearningPathDataRepository extends JpaRepository<LearningPathData, Long> {
    Optional<LearningPathData> findByProfileIdAndQuizId(Long profileId, Long quizId);
    List<LearningPathData> findByProfileId(Long profileId);
    List<LearningPathData> findByQuizId(Long quizId);
}
