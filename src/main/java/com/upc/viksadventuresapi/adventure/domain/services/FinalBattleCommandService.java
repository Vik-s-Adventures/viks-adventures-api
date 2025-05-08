package com.upc.viksadventuresapi.adventure.domain.services;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.FinalBattle;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateFinalBattleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteFinalBattleCommand;

import java.util.Optional;

public interface FinalBattleCommandService {
    Optional<FinalBattle> handle(CreateFinalBattleCommand command);
    void handle(DeleteFinalBattleCommand command);
}