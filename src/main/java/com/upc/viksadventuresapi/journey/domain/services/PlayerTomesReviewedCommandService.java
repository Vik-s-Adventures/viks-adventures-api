package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerTomesReviewedCommand;

import java.util.Optional;

public interface PlayerTomesReviewedCommandService {
    Optional<PlayerTomesReviewed> handle(CreatePlayerTomesReviewedCommand command);
    Optional<PlayerTomesReviewed> handle(UpdatePlayerTomesReviewedCommand command);
    void handle(DeletePlayerTomesReviewedCommand command);
}