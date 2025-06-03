package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record RiddleResource(
        Long id,
        Long trialId,
        String question
) {
}
