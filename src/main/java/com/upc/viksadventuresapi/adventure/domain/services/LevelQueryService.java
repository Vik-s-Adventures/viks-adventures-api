package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllLevelsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLevelByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLevelsByWorldIdQuery;

import java.util.List;
import java.util.Optional;

public interface LevelQueryService {
    Optional<Level> handle(GetLevelByIdQuery query);
    List<Level> handle(GetLevelsByWorldIdQuery query);
    List<Level> handle(GetAllLevelsQuery query);
}
