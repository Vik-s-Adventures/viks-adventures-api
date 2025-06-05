package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllRiddlesDetailQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleDetailByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetRiddleDetailsByRiddleIdQuery;

import java.util.List;
import java.util.Optional;

public interface RiddleDetailQueryService {
    Optional<RiddleDetail> handle(GetRiddleDetailByIdQuery query);
    List<RiddleDetail> handle(GetRiddleDetailsByRiddleIdQuery query);
    List<RiddleDetail> handle(GetAllRiddlesDetailQuery query);
}
