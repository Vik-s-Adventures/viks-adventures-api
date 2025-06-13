package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.RecalculatePlayerProgressCommand;

import java.util.Optional;

public interface PlayerProgressCommandService {
    Optional<PlayerProgress> handle(CreatePlayerProgressCommand command);
    Optional<PlayerProgress> handle(RecalculatePlayerProgressCommand command);
    void handle(DeletePlayerProgressCommand command);
}