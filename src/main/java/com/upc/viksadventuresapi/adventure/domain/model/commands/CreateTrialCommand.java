package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateTrialCommand(
        String levelId,
        String description
) {
}
