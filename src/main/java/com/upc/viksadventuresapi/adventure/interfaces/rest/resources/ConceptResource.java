package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record ConceptResource(
        Long id,
        Long tomeId,
        String subtitle,
        String description,
        String imageUrl
) {
}
