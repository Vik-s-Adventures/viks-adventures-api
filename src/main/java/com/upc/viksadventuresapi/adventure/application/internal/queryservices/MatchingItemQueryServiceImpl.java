package com.upc.viksadventuresapi.adventure.application.internal.queryservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllMatchingItemsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingItemByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingItemsByMatchingPairIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingItemQueryService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingItemQueryServiceImpl implements MatchingItemQueryService {
    private final MatchingItemRepository matchingItemRepository;

    @Override
    public Optional<MatchingItem> handle(GetMatchingItemByIdQuery query) {
        return matchingItemRepository.findById(query.matchingItemId());
    }

    @Override
    public List<MatchingItem> handle(GetMatchingItemsByMatchingPairIdQuery query) {
        return matchingItemRepository.findByMatchingPairId(query.matchingPairId());
    }

    @Override
    public List<MatchingItem> handle(GetAllMatchingItemsQuery query) {
        return matchingItemRepository.findAll();
    }
}
