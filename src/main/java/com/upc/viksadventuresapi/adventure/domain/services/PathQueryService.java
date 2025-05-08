package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Path;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllPathsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetPathByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetPathsByLevelIdQuery;

import java.util.List;
import java.util.Optional;

public interface PathQueryService {
    Optional<Path> handle(GetPathByIdQuery query);
    List<Path> handle(GetPathsByLevelIdQuery query);
    List<Path> handle(GetAllPathsQuery query);
}
