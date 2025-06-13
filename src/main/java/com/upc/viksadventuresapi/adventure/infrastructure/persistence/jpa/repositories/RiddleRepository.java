package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Riddle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiddleRepository extends JpaRepository<Riddle, Long> {
    List<Riddle> findByTrialId(Long trialId);
    List<Riddle> findByTrialLevelId(Long levelId);
}
