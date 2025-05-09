package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record PathResource(
        Long id,
        Long levelId,
        String description,
        String imageUrl
) {
}
