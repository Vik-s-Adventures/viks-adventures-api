package com.upc.viksadventuresapi.journey.domain.model.commands;

public record PlayerMatchingPairRequest(
        Long matchingItemAId,
        Long matchingItemBId
) {}