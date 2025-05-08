package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateWorldCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteWorldCommand;
import com.upc.viksadventuresapi.adventure.domain.services.WorldCommandService;

import java.util.Optional;

public class WorldCommandServiceImpl implements WorldCommandService {
    @Override
    public Optional<World> handle(CreateWorldCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteWorldCommand command) {

    }
}
