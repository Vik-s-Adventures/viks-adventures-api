package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTrialsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialsByLevelIdQuery;

import java.util.List;
import java.util.Optional;

public interface TrialQueryService {
    Optional<Trial> handle(GetTrialByIdQuery query);
    List<Trial> handle(GetTrialsByLevelIdQuery query);
    List<Trial> handle(GetAllTrialsQuery query);
}
