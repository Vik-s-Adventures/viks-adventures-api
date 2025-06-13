package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record CreatePlayerRiddleAnswerResource(
        Long playerId,
        Long riddleDetailId,
        String enteredAnswer
) {
}
