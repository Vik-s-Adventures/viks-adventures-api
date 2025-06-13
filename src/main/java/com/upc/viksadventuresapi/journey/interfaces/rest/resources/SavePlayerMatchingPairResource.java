package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

import java.util.List;

public record SavePlayerMatchingPairResource(
        Long playerId,
        Long matchingId,
        List<PlayerMatchingPairResourceRequest> pairs
) {}
