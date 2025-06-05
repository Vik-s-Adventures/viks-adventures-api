package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateMatchingCommand(
        Long trialId,
        String description
) {
}
