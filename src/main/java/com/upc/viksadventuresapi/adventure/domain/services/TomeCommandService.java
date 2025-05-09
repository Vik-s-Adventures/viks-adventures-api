package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTomeCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteTomeCommand;

import java.util.Optional;

public interface TomeCommandService {
    Optional<Tome> handle(CreateTomeCommand command);
    void handle(DeleteTomeCommand command);
}
