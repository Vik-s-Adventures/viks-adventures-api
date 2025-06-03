package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Riddle;
import com.upc.viksadventuresapi.adventure.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TrialRiddleQueryService {
    Optional<Riddle> handle(GetRiddleByIdQuery query);
    List<Riddle> handle(GetRiddlesByTrialIdQuery query);
    List<Riddle> handle(GetAllRiddlesQuery query);
}
