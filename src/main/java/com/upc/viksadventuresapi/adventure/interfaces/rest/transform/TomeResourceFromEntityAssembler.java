package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.TomeResource;

public class TomeResourceFromEntityAssembler {
    public static TomeResource toResourceFromEntity(Tome entity) {
        return new TomeResource(
                entity.getId(),
                entity.getLevel().getId(),
                entity.getTitle().title(),
                entity.getWelcome().welcome(),
                entity.getAdvice().advice()
        );
    }
}
