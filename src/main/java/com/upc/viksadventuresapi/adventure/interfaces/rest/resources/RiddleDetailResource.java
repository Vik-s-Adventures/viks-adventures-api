package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record RiddleDetailResource(
        Long id,
        Long riddleId,
        String imageUrl,
        String answer
) {
}
