package com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObstacleOptionRepository extends JpaRepository<ObstacleOption, Long> {
    List<ObstacleOption> findByObstacleId(Long obstacleId);
}
