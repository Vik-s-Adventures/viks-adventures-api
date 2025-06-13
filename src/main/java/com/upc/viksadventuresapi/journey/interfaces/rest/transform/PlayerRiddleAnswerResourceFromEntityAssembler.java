package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerRiddleAnswerResource;

public class PlayerRiddleAnswerResourceFromEntityAssembler {
    public static PlayerRiddleAnswerResource toResourceFromEntity(PlayerRiddleAnswer entity){
        return new PlayerRiddleAnswerResource(
                entity.getId(),
                entity.getPlayer().getId(),
                entity.getRiddleDetail().getId(),
                entity.getEnteredAnswer().enteredAnswer()
        );
    }
}
