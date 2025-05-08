package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateLevelCommand(
        Long worldId,
        String name,
        String performance
) {
}
