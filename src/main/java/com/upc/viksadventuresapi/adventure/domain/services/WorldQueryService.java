package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllWorldsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetWorldByIdQuery;

import java.util.List;
import java.util.Optional;

public interface WorldQueryService {
    Optional<World> handle(GetWorldByIdQuery query);
    List<World> handle(GetAllWorldsQuery query);
}
