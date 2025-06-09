package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record CreatePlayerResource(
        Long profileId,
        Integer totalScore
) {
}
