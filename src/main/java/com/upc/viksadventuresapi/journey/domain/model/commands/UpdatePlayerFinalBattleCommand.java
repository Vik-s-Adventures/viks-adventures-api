package com.upc.viksadventuresapi.journey.domain.model.commands;

public record UpdatePlayerFinalBattleCommand(
        Long playerId,
        Long obstacleId,
        Long newObstacleOptionId
) {}