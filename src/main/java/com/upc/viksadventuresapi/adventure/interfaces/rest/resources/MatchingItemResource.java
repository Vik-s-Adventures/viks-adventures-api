package com.upc.viksadventuresapi.adventure.interfaces.rest.resources;

public record MatchingItemResource (
        Long id,
        Long matchingPairId,
        String imageUrl,
        boolean isDistractor
){
}
