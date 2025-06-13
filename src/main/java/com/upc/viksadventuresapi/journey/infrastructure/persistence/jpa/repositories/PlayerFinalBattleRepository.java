package com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerFinalBattleRepository extends JpaRepository<PlayerFinalBattle, Long> {
    List<PlayerFinalBattle> findAllByPlayerIdAndObstacleOptionObstacleFinalBattleLevelId(Long playerId, Long levelId);
    Optional<PlayerFinalBattle> findByPlayerIdAndObstacleOptionObstacleId(Long playerId, Long obstacleId);
}