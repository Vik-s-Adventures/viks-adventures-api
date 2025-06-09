package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record ObstacleResource(
        Long id,
        Long finalBattleId,
        String description,
        String imageUrl
) {
}
