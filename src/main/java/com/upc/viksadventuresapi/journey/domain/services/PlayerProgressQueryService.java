package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerProgressByIdQuery;

import java.util.Optional;

public interface PlayerProgressQueryService {
    Optional<PlayerProgress> handle(GetPlayerProgressByIdQuery query);
}
