package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record CreatePlayerRiddleAnswerResource(
        Long playerProgressId,
        Long riddleDetailId,
        String enteredAnswer
) {
}
