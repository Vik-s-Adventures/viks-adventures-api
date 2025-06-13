package com.upc.viksadventuresapi.journey.domain.model.commands;

public record CreatePlayerTomesReviewedCommand(
        Long playerId,
        Long conceptId
) {
}