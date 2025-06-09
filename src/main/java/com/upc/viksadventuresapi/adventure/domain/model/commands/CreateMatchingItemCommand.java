package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateMatchingItemCommand(
        Long matchingId,
        Long matchingPairId,
        String imageUrl,
        boolean isDistractor
) {
}
