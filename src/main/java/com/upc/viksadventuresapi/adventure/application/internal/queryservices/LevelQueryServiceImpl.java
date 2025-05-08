package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllLevelsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLevelByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLevelsByWorldIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.LevelQueryService;

import java.util.List;
import java.util.Optional;

public class LevelQueryServiceImpl implements LevelQueryService {
    @Override
    public Optional<Level> handle(GetLevelByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Level> handle(GetLevelsByWorldIdQuery query) {
        return List.of();
    }

    @Override
    public List<Level> handle(GetAllLevelsQuery query) {
        return List.of();
    }
}
