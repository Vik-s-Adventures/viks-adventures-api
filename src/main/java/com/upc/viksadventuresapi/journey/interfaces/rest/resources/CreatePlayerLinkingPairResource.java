package com.upc.viksadventuresapi.journey.interfaces.rest.resources;

public record CreatePlayerLinkingPairResource(
        Long playerProgressId,
        Long linkingPairImageId,
        Long linkingPairAnswerId
) {
}
