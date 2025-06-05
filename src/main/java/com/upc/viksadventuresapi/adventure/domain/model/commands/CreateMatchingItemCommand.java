package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateMatchingItemCommand(
        Long matchingPairId,
        String imageUrl,
        boolean isDistractor
) {
}
