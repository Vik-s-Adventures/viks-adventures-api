package com.upc.viksadventuresapi.journey.domain.model.commands;

import java.util.List;

public record SavePlayerLinkingResponseCommand(
    Long playerId,
    List<LinkingPairResponse> pairs
) {
    public record LinkingPairResponse(
        Long linkingPairImageId,
        Long linkingPairAnswerId
    ) {}
}