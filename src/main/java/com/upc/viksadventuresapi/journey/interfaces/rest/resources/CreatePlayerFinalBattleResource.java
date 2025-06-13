package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record CreatePlayerFinalBattleResource(
        Long playerId,
        Long obstacleOptionId
) {
}
