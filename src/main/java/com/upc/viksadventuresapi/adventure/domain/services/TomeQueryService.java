package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTomesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTomeByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTomesByLevelIdQuery;

import java.util.List;
import java.util.Optional;

public interface TomeQueryService {
    Optional<Tome> handle(GetTomeByIdQuery query);
    List<Tome> handle(GetTomesByLevelIdQuery query);
    List<Tome> handle(GetAllTomesQuery query);
}
