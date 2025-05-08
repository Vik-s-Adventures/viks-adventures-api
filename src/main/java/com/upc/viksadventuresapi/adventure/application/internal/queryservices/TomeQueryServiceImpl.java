package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTomesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTomeByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTomesByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.TomeQueryService;

import java.util.List;
import java.util.Optional;

public class TomeQueryServiceImpl implements TomeQueryService {
    @Override
    public Optional<Tome> handle(GetTomeByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Tome> handle(GetTomesByLevelIdQuery query) {
        return List.of();
    }

    @Override
    public List<Tome> handle(GetAllTomesQuery query) {
        return List.of();
    }
}
