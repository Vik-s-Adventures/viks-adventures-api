package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.LinkingResource;

public class LinkingResourceFromEntityAssembler {
    public static LinkingResource toResourceFromEntity(Linking entity) {
        return new LinkingResource(
                entity.getId(),
                entity.getTrial().getId(),
                entity.getDescription().description()
        );
    }
}
