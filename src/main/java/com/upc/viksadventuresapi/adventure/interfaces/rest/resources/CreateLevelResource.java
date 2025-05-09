package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateLevelResource(
        Long worldId,
        String name,
        String performance
) {
}
