package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateObstacleOptionCommand(
        Long obstacleId,
        String optionText,
        Boolean isCorrect
) {
}