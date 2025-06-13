package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record UpdatePlayerFinalBattleResource(
        Long playerId,
        Long obstacleId,
        Long newObstacleOptionId
) {}
