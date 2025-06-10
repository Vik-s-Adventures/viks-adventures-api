package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerCommand;

import java.util.Optional;

public interface PlayerCommandService {
    Optional<Player> handle(CreatePlayerCommand command);
    Optional<Player> handle(UpdatePlayerCommand command, Long playerId);
    void handle(DeletePlayerCommand command);
}
