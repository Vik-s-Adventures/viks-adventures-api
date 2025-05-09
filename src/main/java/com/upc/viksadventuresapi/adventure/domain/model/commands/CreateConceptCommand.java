package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateConceptCommand(
        Long tomeId,
        String subtitle,
        String description,
        String imageUrl
) {
}
