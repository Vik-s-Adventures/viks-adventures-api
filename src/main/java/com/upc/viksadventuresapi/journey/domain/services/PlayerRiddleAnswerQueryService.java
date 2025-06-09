package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerRiddleAnswerByIdQuery;

import java.util.Optional;

public interface PlayerRiddleAnswerQueryService {
    Optional<PlayerRiddleAnswer> handle(GetPlayerRiddleAnswerByIdQuery query);
}
