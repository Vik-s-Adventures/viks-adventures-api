package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerTomesReviewedResource;

public class PlayerTomesReviewedResourceFromEntityAssembler {
    public static PlayerTomesReviewedResource toResourceFromEntity(PlayerTomesReviewed entity){
        return new PlayerTomesReviewedResource(
                entity.getId(),
                entity.getPlayer().getId(),
                entity.getConcept().getId()
        );
    }
}
