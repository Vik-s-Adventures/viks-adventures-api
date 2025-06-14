package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

import java.util.List;

public record SavePlayerMatchingResponseResource(
        Long playerId,
        List<PlayerMatchingPairResource> pairs
) {
    public record PlayerMatchingPairResource(
            Long matchingItemAId,
            Long matchingItemBId
    ) {}
}
