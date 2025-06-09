package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerProgressCommand;

import java.util.Optional;

public interface PlayerProgressCommandService {
    Optional<PlayerProgress> handle(CreatePlayerProgressCommand command);
    void handle(DeletePlayerProgressCommand command);
}
