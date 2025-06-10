package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerLinkingPairResource;

public class PlayerLinkingPairResourceFromEntityAssembler {
    public static PlayerLinkingPairResource toResourceFromEntity(PlayerLinkingPair entity) {
        return new PlayerLinkingPairResource(
                entity.getId(),
                entity.getPlayerProgress().getId(),
                entity.getLinkingPairImage().getId(),
                entity.getLinkingPairAnswer().getId()
        );
    }
}
