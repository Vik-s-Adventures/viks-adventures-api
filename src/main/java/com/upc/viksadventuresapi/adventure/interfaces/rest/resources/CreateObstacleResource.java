package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateObstacleResource(
        Long finalBattleId,
        String imageUrl,
        String description
) {
}
