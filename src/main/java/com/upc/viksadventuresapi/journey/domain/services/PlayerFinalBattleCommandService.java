package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerFinalBattleCommand;

import java.util.Optional;

public interface PlayerFinalBattleCommandService {
    Optional<PlayerFinalBattle> handle(CreatePlayerFinalBattleCommand command);
    Optional<PlayerFinalBattle> handle(UpdatePlayerFinalBattleCommand command);
    void handle(DeletePlayerFinalBattleCommand command);
}