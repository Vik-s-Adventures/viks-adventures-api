package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingPair;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingPairCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingPairCommand;

import java.util.Optional;

public interface MatchingPairCommandService {
    Optional<MatchingPair> handle(CreateMatchingPairCommand command);
    void handle(DeleteMatchingPairCommand command);
}
