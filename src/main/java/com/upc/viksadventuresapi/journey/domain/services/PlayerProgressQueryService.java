package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayerProgressesByLevelIdQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayerProgressesByPlayerIdAndLevelIdQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayerProgressesByPlayerIdAndWorldIdQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerProgressByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PlayerProgressQueryService {
    Optional<PlayerProgress> handle(GetPlayerProgressByIdQuery query);
    List<PlayerProgress> handle(GetAllPlayerProgressesByLevelIdQuery query);
    List<PlayerProgress> handle(GetAllPlayerProgressesByPlayerIdAndLevelIdQuery query);
    List<PlayerProgress> handle(GetAllPlayerProgressesByPlayerIdAndWorldIdQuery query);
}
