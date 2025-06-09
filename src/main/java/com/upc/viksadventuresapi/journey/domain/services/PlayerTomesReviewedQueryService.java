package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerTomesReviewedByIdQuery;

import java.util.Optional;

public interface PlayerTomesReviewedQueryService {
    Optional<PlayerTomesReviewed> handle(GetPlayerTomesReviewedByIdQuery query);
}
