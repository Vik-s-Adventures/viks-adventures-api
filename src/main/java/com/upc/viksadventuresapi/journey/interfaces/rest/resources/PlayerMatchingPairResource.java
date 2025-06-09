package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerMatchingPairResource(
        Long id,
        Long playerProgressId,
        Long matchingItemA,
        Long matchingItemB,
        Boolean isMatched
) {
}
