package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateObstacleCommand(
        Long finalBattleId,
        String description,
        String imageUrl
) {
}
