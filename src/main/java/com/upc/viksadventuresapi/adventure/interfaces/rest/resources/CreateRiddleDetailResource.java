package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateRiddleDetailResource(
        Long riddleId,
        String imageUrl,
        String answer
) {
}
