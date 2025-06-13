package com.upc.viksadventuresapi.journey.domain.model.commands;

public record PlayerLinkingPairRequest(
        Long linkingPairId,
        Long linkingPairImageId,
        Long linkingPairAnswerId
) {}