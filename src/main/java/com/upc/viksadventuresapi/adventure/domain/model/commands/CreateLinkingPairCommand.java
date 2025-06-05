package com.upc.viksadventuresapi.adventure.domain.model.commands;

public record CreateLinkingPairCommand(
        Long linkingId,
        String imageUrl,
        String answer
) {
}
