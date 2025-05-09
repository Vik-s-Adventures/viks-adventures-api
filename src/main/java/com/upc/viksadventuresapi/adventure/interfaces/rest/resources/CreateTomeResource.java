package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateTomeResource(
        Long levelId,
        String title,
        String welcome,
        String advice
) {
}
