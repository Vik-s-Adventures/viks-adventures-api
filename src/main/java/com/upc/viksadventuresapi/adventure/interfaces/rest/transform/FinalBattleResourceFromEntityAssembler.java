package com.upc.viksadventuresapi.adventure.interfaces.rest.transform;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.FinalBattle;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.FinalBattleResource;

public class FinalBattleResourceFromEntityAssembler {
    public static FinalBattleResource toResourceFromEntity(FinalBattle entity){
        return new FinalBattleResource(
                entity.getId(),
                entity.getLevel().getId(),
                entity.getDescription().description()
        );
    }
}
