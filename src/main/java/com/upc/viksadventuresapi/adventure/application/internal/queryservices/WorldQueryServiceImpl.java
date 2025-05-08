package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllWorldsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetWorldByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.WorldQueryService;

import java.util.List;
import java.util.Optional;

public class WorldQueryServiceImpl implements WorldQueryService {
    @Override
    public Optional<World> handle(GetWorldByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<World> handle(GetAllWorldsQuery query) {
        return List.of();
    }
}
