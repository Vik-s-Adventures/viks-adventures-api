package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingPair;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllMatchingPairsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingPairByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingPairsByMatchingIdQuery;

import java.util.List;
import java.util.Optional;

public interface MatchingPairQueryService {
    Optional<MatchingPair> handle(GetMatchingPairByIdQuery query);
    List<MatchingPair> handle(GetMatchingPairsByMatchingIdQuery query);
    List<MatchingPair> handle(GetAllMatchingPairsQuery query);
}