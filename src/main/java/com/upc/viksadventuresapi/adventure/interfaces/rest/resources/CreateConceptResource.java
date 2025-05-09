package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateConceptResource(
        Long tomeId,
        String subtitle,
        String description,
        String imageUrl
) {
}
