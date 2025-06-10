package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerLinkingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerLinkingPairCommand;

import java.util.Optional;

public interface PlayerLinkingPairCommandService {
    Optional<PlayerLinkingPair> handle(CreatePlayerLinkingPairCommand command);
    void handle(DeletePlayerLinkingPairCommand command);
}
