package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllMatchesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchesByTrialIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MatchingQueryService {
    Optional<Matching> handle(GetMatchingByIdQuery query);
    List<Matching> handle(GetMatchesByTrialIdQuery query);
    List<Matching> handle(GetAllMatchesQuery query);
}
