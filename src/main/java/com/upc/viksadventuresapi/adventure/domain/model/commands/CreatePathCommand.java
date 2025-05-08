package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreatePathCommand(
        String levelId,
        String description,
        String imageUrl
) {
}
