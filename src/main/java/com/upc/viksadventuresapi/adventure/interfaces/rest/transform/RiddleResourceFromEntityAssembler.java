package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Riddle;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.RiddleResource;

public class RiddleResourceFromEntityAssembler {
    public static RiddleResource toResourceFromEntity(Riddle entity) {
        return new RiddleResource(
                entity.getId(),
                entity.getTrial().getId(),
                entity.getQuestion().question()
        );
    }
}