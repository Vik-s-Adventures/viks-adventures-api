package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateWorldCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteWorldCommand;

import java.util.Optional;

public interface WorldCommandService {
    Optional<World> handle(CreateWorldCommand command);
    void handle(DeleteWorldCommand command);
}
