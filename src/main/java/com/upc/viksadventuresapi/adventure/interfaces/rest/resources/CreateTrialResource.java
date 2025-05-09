package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateTrialResource(
        Long levelId,
        String description
) {
}
