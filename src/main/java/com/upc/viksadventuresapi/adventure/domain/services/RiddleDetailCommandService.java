package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateRiddleDetailCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteRiddleDetailCommand;

import java.util.Optional;

public interface RiddleDetailCommandService {
    Optional<RiddleDetail> handle(CreateRiddleDetailCommand command);
    void handle(DeleteRiddleDetailCommand command);
}
