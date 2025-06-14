package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

import java.util.List;

public record SavePlayerLinkingResponseResource(
        Long playerId,
        List<PlayerLinkingPairResource> pairs
) {
    public record PlayerLinkingPairResource(
            Long linkingPairImageId,
            Long linkingPairAnswerId
    ) {}
}