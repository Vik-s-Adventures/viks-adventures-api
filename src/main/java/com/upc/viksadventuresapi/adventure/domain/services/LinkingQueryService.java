package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllLinksQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinksByTrialIdQuery;

import java.util.List;
import java.util.Optional;

public interface LinkingQueryService {
    Optional<Linking> handle(GetLinkingByIdQuery query);
    List<Linking> handle(GetLinksByTrialIdQuery query);
    List<Linking> handle(GetAllLinksQuery query);
}
