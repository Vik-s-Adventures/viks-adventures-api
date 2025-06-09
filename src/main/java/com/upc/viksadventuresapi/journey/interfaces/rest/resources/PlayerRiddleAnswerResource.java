package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerRiddleAnswerResource(
        Long id,
        Long playerProgressId,
        Long riddleDetailId,
        String enteredAnswer
) {
}
