package com.upc.viksadventuresapi.journey.application.internal.queryservices;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerFinalBattleByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerFinalBattleQueryService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerFinalBattleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerFinalBattleQueryServiceImpl implements PlayerFinalBattleQueryService {
    private final PlayerFinalBattleRepository playerFinalBattleRepository;

    @Override
    public Optional<PlayerFinalBattle> handle(GetPlayerFinalBattleByIdQuery query) {
        return playerFinalBattleRepository.findById(query.playerFinalBattleId());
    }
}
