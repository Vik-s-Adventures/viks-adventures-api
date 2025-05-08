package com.upc.viksadventuresapi.adventure.domain.model.commands;

import com.upc.viksadventuresapi.adventure.domain.model.enums.CompetenceType;

public record CreateWorldCommand(
        String name,
        CompetenceType competenceType
) {
}