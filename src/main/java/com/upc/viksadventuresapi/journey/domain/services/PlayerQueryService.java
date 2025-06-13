package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayersQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PlayerQueryService {
    Optional<Player> handle(GetPlayerByIdQuery query);
    List<Player> handle(GetAllPlayersQuery query);
}
