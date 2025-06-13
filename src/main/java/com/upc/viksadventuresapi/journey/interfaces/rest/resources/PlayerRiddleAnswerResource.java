package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerRiddleAnswerResource(
        Long id,
        Long playerId,
        Long riddleDetailId,
        String enteredAnswer
) {
}
