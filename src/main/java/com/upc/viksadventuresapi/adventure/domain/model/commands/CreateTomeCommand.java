package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateTomeCommand(
        Long levelId,
        String title,
        String welcome,
        String advice
) {
}
