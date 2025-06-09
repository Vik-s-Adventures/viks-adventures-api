package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.MatchingItemResource;

public class MatchingItemResourceFromEntityAssembler {
    public static MatchingItemResource toResourceFromEntity(MatchingItem entity) {
        return new MatchingItemResource(
                entity.getId(),
                entity.getMatching().getId(),
                entity.getMatchingPair().getId(),
                entity.getImageUrl().imageUrl(),
                entity.isDistractor()
        );
    }
}
