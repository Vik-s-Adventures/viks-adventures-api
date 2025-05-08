package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLevelCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLevelCommand;
import com.upc.viksadventuresapi.adventure.domain.services.LevelCommandService;

import java.util.Optional;

public class LevelCommandServiceImpl implements LevelCommandService {
    @Override
    public Optional<Level> handle(CreateLevelCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteLevelCommand command) {

    }
}
