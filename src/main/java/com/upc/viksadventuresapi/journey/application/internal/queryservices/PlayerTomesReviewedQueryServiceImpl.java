package com.upc.viksadventuresapi.journey.application.internal.queryservices;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerTomesReviewedByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerTomesReviewedQueryService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerTomesReviewedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerTomesReviewedQueryServiceImpl implements PlayerTomesReviewedQueryService {
    private final PlayerTomesReviewedRepository playerTomesReviewedRepository;

    @Override
    public Optional<PlayerTomesReviewed> handle(GetPlayerTomesReviewedByIdQuery query) {
        return playerTomesReviewedRepository.findById(query.playerTomesReviewedId());
    }
}
