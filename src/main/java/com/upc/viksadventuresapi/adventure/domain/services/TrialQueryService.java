package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTrialsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTrialsByWorldIdQuery;

import java.util.List;
import java.util.Optional;

public interface TrialQueryService {
    Optional<Trial> handle(GetTrialByIdQuery query);
    List<Trial> handle(GetTrialsByWorldIdQuery query);
    List<Trial> handle(GetAllTrialsQuery query);
}
