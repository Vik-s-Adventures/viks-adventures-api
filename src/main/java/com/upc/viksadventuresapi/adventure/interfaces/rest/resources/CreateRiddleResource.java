package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateRiddleResource(
        Long trialId,
        String question
) {
}
