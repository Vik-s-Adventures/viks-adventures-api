package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateObstacleOptionCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteObstacleOptionCommand;

import java.util.Optional;

public interface ObstacleOptionCommandService {
    Optional<ObstacleOption> handle(CreateObstacleOptionCommand command);
    void handle(DeleteObstacleOptionCommand command);
}
