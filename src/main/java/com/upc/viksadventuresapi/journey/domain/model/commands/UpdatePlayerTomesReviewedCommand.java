package com.upc.viksadventuresapi.journey.domain.model.commands;

public record UpdatePlayerTomesReviewedCommand(
    Long playerId,
    Long conceptId
) {}