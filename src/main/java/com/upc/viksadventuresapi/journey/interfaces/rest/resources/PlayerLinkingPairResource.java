package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerLinkingPairResource(
        Long id,
        Long playerId,
        Long linkingPairImageId,
        Long linkingPairAnswerId
) {}