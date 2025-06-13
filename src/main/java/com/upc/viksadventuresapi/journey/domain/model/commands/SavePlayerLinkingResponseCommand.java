package com.upc.viksadventuresapi.journey.domain.model.commands;

import java.util.List;

public record SavePlayerLinkingResponseCommand(
        Long playerId,
        Long linkingId,
        List<PlayerLinkingPairRequest> pairs
) {}