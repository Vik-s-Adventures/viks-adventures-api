package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record TomeResource(
        Long id,
        Long levelId,
        String title,
        String welcome,
        String advice
) {
}
