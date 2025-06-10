package com.upc.viksadventuresapi.journey.domain.model.commands;

public record CreatePlayerLinkingPairCommand(
        Long playerProgressId,
        Long linkingPairImageId,
        Long linkingPairAnswerId
) {
}