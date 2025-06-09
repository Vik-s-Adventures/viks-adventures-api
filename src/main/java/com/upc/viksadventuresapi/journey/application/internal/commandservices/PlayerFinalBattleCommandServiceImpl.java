package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ObstacleOptionRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.domain.services.PlayerFinalBattleCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerFinalBattleRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerFinalBattleCommandServiceImpl implements PlayerFinalBattleCommandService {
    private final PlayerFinalBattleRepository playerFinalBattleRepository;
    private final PlayerProgressRepository playerProgressRepository;
    private final ObstacleOptionRepository obstacleOptionRepository;

    @Override
    public Optional<PlayerFinalBattle> handle(CreatePlayerFinalBattleCommand command) {
        Optional< PlayerProgress> optionalPlayerProgress = playerProgressRepository.findById(command.playerProgressId());
        Optional<ObstacleOption> optionalObstacleOption = obstacleOptionRepository.findById(command.obstacleOptionId());

        var playerFinalBattle = getPlayerFinalBattle(command, optionalObstacleOption, optionalPlayerProgress);

        try {
            playerFinalBattleRepository.save(playerFinalBattle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving PlayerFinalBattle: " + e.getMessage());
        }
        
        return Optional.of(playerFinalBattle);
    }

    private static PlayerFinalBattle getPlayerFinalBattle(CreatePlayerFinalBattleCommand command, Optional<ObstacleOption> optionalObstacleOption, Optional<PlayerProgress> optionalPlayerProgress) {
        if (optionalObstacleOption.isEmpty()) {
            throw new IllegalArgumentException("ObstacleOption with ID " + command.obstacleOptionId() + " does not exist.");
        }
        if (optionalPlayerProgress.isEmpty()) {
            throw new IllegalArgumentException("PlayerProgress with ID " + command.playerProgressId() + " does not exist.");
        }

        PlayerProgress playerProgress = optionalPlayerProgress.get();
        ObstacleOption obstacleOption = optionalObstacleOption.get();
        return new PlayerFinalBattle(playerProgress, obstacleOption, command);
    }

    @Override
    public void handle(DeletePlayerFinalBattleCommand command) {
        if (!playerFinalBattleRepository.existsById(command.playerFinalBattleId())) {
            throw new IllegalArgumentException("PlayerFinalBattle with ID " + command.playerFinalBattleId() + " does not exist.");
        }
        try {
            playerFinalBattleRepository.deleteById(command.playerFinalBattleId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting PlayerFinalBattle with ID " + command.playerFinalBattleId());
        }
    }
}
