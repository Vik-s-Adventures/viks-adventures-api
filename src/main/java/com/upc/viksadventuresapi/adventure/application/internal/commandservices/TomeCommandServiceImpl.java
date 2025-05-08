package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTomeCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteTomeCommand;
import com.upc.viksadventuresapi.adventure.domain.services.TomeCommandService;

import java.util.Optional;

public class TomeCommandServiceImpl implements TomeCommandService {
    @Override
    public Optional<Tome> handle(CreateTomeCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteTomeCommand command) {

    }
}
