package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.World;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllWorldsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetWorldByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.WorldQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.WorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorldQueryServiceImpl implements WorldQueryService {
    private final WorldRepository worldRepository;

    @Override
    public Optional<World> handle(GetWorldByIdQuery query) {
        return worldRepository.findById(query.worldId());
    }

    @Override
    public List<World> handle(GetAllWorldsQuery query) {
        return worldRepository.findAll();
    }
}
