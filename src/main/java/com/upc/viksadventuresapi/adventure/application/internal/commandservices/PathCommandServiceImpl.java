package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Path;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreatePathCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeletePathCommand;
import com.upc.viksadventuresapi.adventure.domain.services.PathCommandService;

import java.util.Optional;

public class PathCommandServiceImpl implements PathCommandService {
    @Override
    public Optional<Path> handle(CreatePathCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeletePathCommand command) {

    }
}
