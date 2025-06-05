package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllLinkingPairsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingPairByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingPairsByLinkingIdQuery;

import java.util.List;
import java.util.Optional;

public interface LinkingPairQueryService {
    Optional<LinkingPair> handle(GetLinkingPairByIdQuery query);
    List<LinkingPair> handle(GetLinkingPairsByLinkingIdQuery query);
    List<LinkingPair> handle(GetAllLinkingPairsQuery query);
}