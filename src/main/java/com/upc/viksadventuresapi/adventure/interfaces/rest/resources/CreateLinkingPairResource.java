package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateLinkingPairResource(
        Long linkinId,
        String imageUrl,
        String answer
) {
}
