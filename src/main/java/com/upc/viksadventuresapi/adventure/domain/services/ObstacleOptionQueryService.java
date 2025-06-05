package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllObstacleOptionsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleOptionByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleOptionsByObstacleIdQuery;

import java.util.List;
import java.util.Optional;

public interface ObstacleOptionQueryService {
    Optional<ObstacleOption> handle(GetObstacleOptionByIdQuery query);
    List<ObstacleOption> handle(GetObstacleOptionsByObstacleIdQuery query);
    List<ObstacleOption> handle(GetAllObstacleOptionsQuery query);
}
