package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingCommand;

import java.util.Optional;

public interface MatchingCommandService {
    Optional<Matching> handle(CreateMatchingCommand command);
    void handle(DeleteMatchingCommand command);
}