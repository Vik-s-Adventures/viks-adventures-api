package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLevelCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLevelCommand;

import java.util.Optional;

public interface LevelCommandService {
    Optional<Level> handle(CreateLevelCommand command);
    void handle(DeleteLevelCommand command);
}
