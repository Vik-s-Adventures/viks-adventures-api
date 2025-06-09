package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerTomesReviewedCommand;

import java.util.Optional;

public interface PlayerTomesReviewedCommandService {
    Optional<PlayerTomesReviewed> handle(CreatePlayerTomesReviewedCommand command);
    void handle(DeletePlayerTomesReviewedCommand command);
}
