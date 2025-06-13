package com.upc.viksadventuresapi.journey.domain.model.commands;

public record RecalculatePlayerProgressCommand(
        Long playerId,
        Long levelId
) { }