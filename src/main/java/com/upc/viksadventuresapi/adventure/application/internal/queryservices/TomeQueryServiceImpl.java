package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTomesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTomeByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTomesByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.TomeQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.TomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TomeQueryServiceImpl implements TomeQueryService {
    private final TomeRepository tomeRepository;

    @Override
    public Optional<Tome> handle(GetTomeByIdQuery query) {
        return tomeRepository.findById(query.tomeId());
    }

    @Override
    public List<Tome> handle(GetTomesByLevelIdQuery query) {
        return tomeRepository.findByLevelId(query.levelId());
    }

    @Override
    public List<Tome> handle(GetAllTomesQuery query) {
        return tomeRepository.findAll();
    }
}
