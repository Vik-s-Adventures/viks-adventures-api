package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerMatchingPairResourceRequest(
        Long matchingItemAId,
        Long matchingItemBId
) {}
