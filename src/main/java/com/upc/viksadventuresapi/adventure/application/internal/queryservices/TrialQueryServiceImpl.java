package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTrialsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialsByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.TrialQueryService;

import java.util.List;
import java.util.Optional;

public class TrialQueryServiceImpl implements TrialQueryService {
    @Override
    public Optional<Trial> handle(GetTrialByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Trial> handle(GetTrialsByLevelIdQuery query) {
        return List.of();
    }

    @Override
    public List<Trial> handle(GetAllTrialsQuery query) {
        return List.of();
    }
}
