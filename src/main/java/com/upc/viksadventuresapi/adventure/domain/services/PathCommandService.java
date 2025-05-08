package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Path;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreatePathCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeletePathCommand;

import java.util.Optional;

public interface PathCommandService {
    Optional<Path> handle(CreatePathCommand command);
    void handle(DeletePathCommand command);
}
