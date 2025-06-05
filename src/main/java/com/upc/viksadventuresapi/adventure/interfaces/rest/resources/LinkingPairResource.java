package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record LinkingPairResource(
        Long id,
        Long linkinId,
        String imageUrl,
        String answer
) {
}
