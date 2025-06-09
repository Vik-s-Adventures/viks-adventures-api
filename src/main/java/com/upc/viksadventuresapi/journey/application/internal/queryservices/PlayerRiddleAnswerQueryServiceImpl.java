package com.upc.viksadventuresapi.journey.application.internal.queryservices;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerRiddleAnswerByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerRiddleAnswerQueryService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRiddleAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerRiddleAnswerQueryServiceImpl implements PlayerRiddleAnswerQueryService {
    private final PlayerRiddleAnswerRepository playerRiddleAnswerRepository;

    @Override
    public Optional<PlayerRiddleAnswer> handle(GetPlayerRiddleAnswerByIdQuery query) {
        return playerRiddleAnswerRepository.findById(query.playerRiddleAnswerId());
    }
}
