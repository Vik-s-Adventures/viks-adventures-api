package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreatePathCommand(
        Long levelId,
        String description,
        String imageUrl
) {
}
