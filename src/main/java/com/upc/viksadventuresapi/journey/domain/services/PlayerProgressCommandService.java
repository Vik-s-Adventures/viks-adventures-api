package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.*;

import java.util.List;
import java.util.Optional;

public interface PlayerProgressCommandService {
    Optional<PlayerProgress> handle(CreatePlayerProgressCommand command);
    List<PlayerProgress> handle(BulkCreatePlayerProgressCommand command);
    List<PlayerProgress> handle(SyncPlayerProgressCommand command);
    Optional<PlayerProgress> handle(RecalculatePlayerProgressCommand command);
    void handle(DeletePlayerProgressCommand command);
}