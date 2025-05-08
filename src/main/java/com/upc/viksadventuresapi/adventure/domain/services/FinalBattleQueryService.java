package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.FinalBattle;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllFinalBattlesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetFinalBattleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetFinalBattlesByWorldIdQuery;

import java.util.List;
import java.util.Optional;

public interface FinalBattleQueryService {
    Optional<FinalBattle> handle(GetFinalBattleByIdQuery query);
    List<FinalBattle> handle(GetFinalBattlesByWorldIdQuery query);
    List<FinalBattle> handle(GetAllFinalBattlesQuery query);
}
