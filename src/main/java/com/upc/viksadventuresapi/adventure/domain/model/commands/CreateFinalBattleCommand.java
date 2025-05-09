package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateFinalBattleCommand(
        Long levelId,
        String description
) {
}
