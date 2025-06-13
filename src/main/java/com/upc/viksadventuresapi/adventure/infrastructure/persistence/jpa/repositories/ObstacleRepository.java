package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Obstacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObstacleRepository extends JpaRepository<Obstacle, Long> {
    List<Obstacle> findByFinalBattleId(Long finalBattleId);
    List<Obstacle> findByFinalBattleLevelId(Long levelId);
}
