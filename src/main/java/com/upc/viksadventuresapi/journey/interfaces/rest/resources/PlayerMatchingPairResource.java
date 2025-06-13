package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerMatchingPairResource(
        Long id,
        Long playerId,
        Long matchingId,
        Long matchingItemAId,
        Long matchingItemBId
) {
}