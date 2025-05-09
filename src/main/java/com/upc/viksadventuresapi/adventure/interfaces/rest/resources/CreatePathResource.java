package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreatePathResource(
        Long levelId,
        String description,
        String imageUrl
) {
}
