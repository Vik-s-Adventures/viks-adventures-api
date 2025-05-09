package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateTrialCommand(
        Long levelId,
        String description
) {
}
