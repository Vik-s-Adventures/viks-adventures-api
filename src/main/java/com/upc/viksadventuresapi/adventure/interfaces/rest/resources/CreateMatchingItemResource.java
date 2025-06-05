package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record CreateMatchingItemResource(
        Long matchingId,
        Long matchingPairId,
        String imageUrl,
        boolean isDistractor
) {
}
