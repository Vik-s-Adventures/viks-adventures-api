package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

import java.util.List;

public record SavePlayerLinkingResponseResource(
        Long playerId,
        Long linkingId,
        List<PlayerLinkingPairResourceRequest> pairs
) {}