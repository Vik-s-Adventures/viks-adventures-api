package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingPair;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.MatchingPairResource;

public class MatchingPairResourceFromEntityAssembler {
    public static MatchingPairResource toResourceFromEntity(MatchingPair entity){
        return new MatchingPairResource(
                entity.getId(),
                entity.getMatching().getId()
        );
    }
}
