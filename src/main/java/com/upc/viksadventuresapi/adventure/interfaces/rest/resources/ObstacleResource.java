package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record ObstacleResource(
        Long id,
        Long finalBattleId,
        String imageUrl,
        String description
) {
}
