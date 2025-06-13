package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record UpdatePlayerRiddleAnswerResource(
        Long playerId,
        Long riddleDetailId,
        String enteredAnswer
) {}

