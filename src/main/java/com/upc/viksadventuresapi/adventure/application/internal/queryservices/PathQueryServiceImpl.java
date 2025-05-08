package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Path;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllPathsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetPathByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetPathsByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.PathQueryService;

import java.util.List;
import java.util.Optional;

public class PathQueryServiceImpl implements PathQueryService {
    @Override
    public Optional<Path> handle(GetPathByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Path> handle(GetPathsByLevelIdQuery query) {
        return List.of();
    }

    @Override
    public List<Path> handle(GetAllPathsQuery query) {
        return List.of();
    }
}
