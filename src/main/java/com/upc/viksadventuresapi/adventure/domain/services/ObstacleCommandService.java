package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Obstacle;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateObstacleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteObstacleCommand;

import java.util.Optional;

public interface ObstacleCommandService {
    Optional<Obstacle> handle(CreateObstacleCommand command);
    void handle(DeleteObstacleCommand command);
}
