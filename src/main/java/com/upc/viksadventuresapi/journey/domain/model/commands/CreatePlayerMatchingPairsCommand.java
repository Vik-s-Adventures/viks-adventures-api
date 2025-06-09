package com.upc.viksadventuresapi.journey.domain.model.commands;

public record CreatePlayerMatchingPairsCommand(
        Long playerProgressId,
        Long matchingItemAId,
        Long matchingItemBId
) {
}
