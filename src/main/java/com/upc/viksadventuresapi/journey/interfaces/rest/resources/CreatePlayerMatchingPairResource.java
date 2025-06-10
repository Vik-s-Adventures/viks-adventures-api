package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record CreatePlayerMatchingPairResource(
        Long playerProgressId,
        Long matchingItemA,
        Long matchingItemB
) {}
