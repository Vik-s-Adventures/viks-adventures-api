package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.FinalBattle;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllFinalBattlesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetFinalBattleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetFinalBattlesByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.FinalBattleQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.FinalBattleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinalBattleQueryServiceImpl implements FinalBattleQueryService {
    private final FinalBattleRepository finalBattleRepository;

    @Override
    public Optional<FinalBattle> handle(GetFinalBattleByIdQuery query) {
        return finalBattleRepository.findById(query.finalBattleId());
    }

    @Override
    public List<FinalBattle> handle(GetFinalBattlesByLevelIdQuery query) {
        return finalBattleRepository.findByLevelId(query.levelId());
    }

    @Override
    public List<FinalBattle> handle(GetAllFinalBattlesQuery query) {
        return finalBattleRepository.findAll();
    }
}
