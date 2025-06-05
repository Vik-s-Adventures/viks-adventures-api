package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.LinkingPairResource;

public class LinkingPairResourceFromEntityAssembler {
    public static LinkingPairResource toResourceFromEntity(LinkingPair entity) {
        return new LinkingPairResource(
                entity.getId(),
                entity.getLinking().getId(),
                entity.getImageUrl().imageUrl(),
                entity.getAnswer().answer()
        );
    }
}
