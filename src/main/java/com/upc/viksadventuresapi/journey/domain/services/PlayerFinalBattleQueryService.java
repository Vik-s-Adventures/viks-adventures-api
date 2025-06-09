package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerFinalBattleByIdQuery;

import java.util.Optional;

public interface PlayerFinalBattleQueryService {
    Optional<PlayerFinalBattle> handle(GetPlayerFinalBattleByIdQuery query);
}
