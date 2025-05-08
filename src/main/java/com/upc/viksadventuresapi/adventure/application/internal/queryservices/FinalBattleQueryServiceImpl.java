package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.FinalBattle;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllFinalBattlesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetFinalBattleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetFinalBattlesByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.FinalBattleQueryService;

import java.util.List;
import java.util.Optional;

public class FinalBattleQueryServiceImpl implements FinalBattleQueryService {
    @Override
    public Optional<FinalBattle> handle(GetFinalBattleByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<FinalBattle> handle(GetFinalBattlesByLevelIdQuery query) {
        return List.of();
    }

    @Override
    public List<FinalBattle> handle(GetAllFinalBattlesQuery query) {
        return List.of();
    }
}
