package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateLinkingCommand(
        Long trialId,
        String description
) {
}
