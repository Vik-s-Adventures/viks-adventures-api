package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerMatchingPairResource;

public class PlayerMatchingPairResourceFromEntityAssembler {
    public static PlayerMatchingPairResource toResourceFromEntity(PlayerMatchingPair entity) {
        return new PlayerMatchingPairResource(
                entity.getId(),
                entity.getPlayerProgress().getId(),
                entity.getMatchingItemA().getId(),
                entity.getMatchingItemB().getId(),
                entity.getIsMatched()
        );
    }
}
