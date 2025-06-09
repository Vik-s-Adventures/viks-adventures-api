package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerMatchingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerMatchingPairCommand;

import java.util.Optional;

public interface PlayerMatchingPairCommandService {
    Optional<PlayerMatchingPair> handle(CreatePlayerMatchingPairCommand command);
    void handle(DeletePlayerMatchingPairCommand command);
}
