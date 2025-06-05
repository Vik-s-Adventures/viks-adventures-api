package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record ObstacleOptionResource(
        Long id,
        Long obstacleId,
        String optionText,
        Boolean isCorrect
) {
}
