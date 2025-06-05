package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Obstacle;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllObstaclesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstaclesByFinalBattleIdQuery;

import java.util.List;
import java.util.Optional;

public interface ObstacleQueryService {
    Optional<Obstacle> handle(GetObstacleByIdQuery query);
    List<Obstacle> handle(GetObstaclesByFinalBattleIdQuery query);
    List<Obstacle> handle(GetAllObstaclesQuery query);
}
