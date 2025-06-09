package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerResource(
        Long id,
        Long profileId,
        Integer totalScore
) {
}
