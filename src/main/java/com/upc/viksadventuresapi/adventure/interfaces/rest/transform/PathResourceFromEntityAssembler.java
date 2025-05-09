package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Path;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.PathResource;

public class PathResourceFromEntityAssembler {
    public static PathResource toResourceFromEntity(Path entity) {
        return new PathResource(
                entity.getId(),
                entity.getLevel().getId(),
                entity.getDescription().description(),
                entity.getImageUrl().imageUrl()
        );
    }
}
