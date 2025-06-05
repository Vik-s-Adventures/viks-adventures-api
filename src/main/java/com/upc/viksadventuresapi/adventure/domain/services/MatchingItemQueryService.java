package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllMatchingItemsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingItemByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetMatchingItemsByMatchingPairIdQuery;

import java.util.List;
import java.util.Optional;

public interface MatchingItemQueryService {
    Optional<MatchingItem> handle(GetMatchingItemByIdQuery query);
    List<MatchingItem> handle(GetMatchingItemsByMatchingPairIdQuery query);
    List<MatchingItem> handle(GetAllMatchingItemsQuery query);
}