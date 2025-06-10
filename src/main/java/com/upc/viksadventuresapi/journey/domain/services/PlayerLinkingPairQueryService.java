package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerLinkingPairByIdQuery;

import java.util.Optional;

public interface PlayerLinkingPairQueryService {
    Optional<PlayerLinkingPair> handle(GetPlayerLinkingPairByIdQuery query);
}
