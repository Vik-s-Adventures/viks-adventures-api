package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record PlayerLinkingPairResource(
        Long id,
        Long playerProgressId,
        Long linkingPairImageId,
        Long linkingPairAnswerId
) {
}
