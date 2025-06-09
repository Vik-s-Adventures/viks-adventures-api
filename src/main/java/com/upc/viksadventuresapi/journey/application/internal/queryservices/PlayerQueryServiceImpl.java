package com.upc.viksadventuresapi.journey.application.internal.queryservices;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerQueryService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerQueryServiceImpl implements PlayerQueryService {
    private final PlayerRepository playerRepository;

    @Override
    public Optional<Player> handle(GetPlayerByIdQuery query) {
        return playerRepository.findById(query.playerId());
    }
}
