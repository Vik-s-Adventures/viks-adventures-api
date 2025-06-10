package com.upc.viksadventuresapi.journey.domain.model.commands;

public record CreatePlayerMatchingPairCommand(
        Long playerProgressId,
        Long matchingItemA,
        Long matchingItemB
) {
}
