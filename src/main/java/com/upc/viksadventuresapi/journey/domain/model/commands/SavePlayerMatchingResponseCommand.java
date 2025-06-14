package com.upc.viksadventuresapi.journey.domain.model.commands;

import java.util.List;

public record SavePlayerMatchingResponseCommand(
    Long playerId,
    List<MatchingPairResponse> pairs
) {
    public record MatchingPairResponse(
        Long matchingItemAId,
        Long matchingItemBId
    ) {}
}