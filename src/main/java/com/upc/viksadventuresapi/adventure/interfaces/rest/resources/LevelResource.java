package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record LevelResource(
        Long id,
        Long worldId,
        String name,
        String performance
) {
}
