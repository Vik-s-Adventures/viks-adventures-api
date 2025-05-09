package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateWorldCommand(
        String name,
        String competenceType
) {
}