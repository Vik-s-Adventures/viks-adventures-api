package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.RiddleDetailResource;

public class RiddleDetailResourceFromEntityAssembler {
    public static RiddleDetailResource toResourceFromEntity(RiddleDetail entity){
        return new RiddleDetailResource(
                entity.getId(),
                entity.getRiddle().getId(),
                entity.getImageUrl().imageUrl(),
                entity.getAnswer().answer()
        );
    }
}
