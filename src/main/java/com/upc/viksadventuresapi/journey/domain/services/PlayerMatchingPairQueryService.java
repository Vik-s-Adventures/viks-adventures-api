package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerMatchingPairByIdQuery;

import java.util.Optional;

public interface PlayerMatchingPairQueryService {
    Optional<PlayerMatchingPair> handle(GetPlayerMatchingPairByIdQuery query);
}
