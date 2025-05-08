package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.FinalBattle;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateFinalBattleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteFinalBattleCommand;
import com.upc.viksadventuresapi.adventure.domain.services.FinalBattleCommandService;

import java.util.Optional;

public class FinalBattleCommandServiceImpl implements FinalBattleCommandService {
    @Override
    public Optional<FinalBattle> handle(CreateFinalBattleCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteFinalBattleCommand command) {

    }
}
