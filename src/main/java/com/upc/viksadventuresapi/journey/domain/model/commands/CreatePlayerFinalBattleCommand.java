package com.upc.viksadventuresapi.journey.domain.model.commands;

public record CreatePlayerFinalBattleCommand(
        Long playerProgressId,
        Long obstacleOptionId
) {
}
