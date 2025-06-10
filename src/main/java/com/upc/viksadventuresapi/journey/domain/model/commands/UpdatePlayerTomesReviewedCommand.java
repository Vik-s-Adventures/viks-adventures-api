package com.upc.viksadventuresapi.journey.domain.model.commands;

public record UpdatePlayerTomesReviewedCommand(
    Long playerProgressId,
    Long conceptId
) {}