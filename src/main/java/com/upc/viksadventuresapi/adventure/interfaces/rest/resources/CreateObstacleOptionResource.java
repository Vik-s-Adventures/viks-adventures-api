package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateObstacleOptionResource(
        Long obstacleId,
        String optionText,
        Boolean isCorrect
) {
}
