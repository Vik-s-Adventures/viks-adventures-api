package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateRiddleDetailCommand(
        Long riddleId,
        String imageUrl,
        String answer
) {
}
