package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerTotalScoreCommand;

import java.util.Optional;

public interface PlayerCommandService {
    Optional<Player> handle(CreatePlayerCommand command);
    void handle(UpdatePlayerTotalScoreCommand command);
    void handle(DeletePlayerCommand command);
}