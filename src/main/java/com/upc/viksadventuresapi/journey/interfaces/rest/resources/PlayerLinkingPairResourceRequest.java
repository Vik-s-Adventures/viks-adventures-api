package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerLinkingPairResourceRequest(
        Long linkingPairId,
        Long linkingPairImageId,
        Long linkingPairAnswerId
) {}
