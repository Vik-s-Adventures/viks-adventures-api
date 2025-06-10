package com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Long> {
    Optional<PlayerProgress> findByPlayerIdAndLevelId(Long playerId, Long levelIdOfObstacle);
}
