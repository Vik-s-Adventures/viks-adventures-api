package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.RecalculatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.services.PlayerProgressCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerFinalBattleEventListener {

    private final PlayerProgressCommandService playerProgressCommandService;
    private final PlayerProgressRepository playerProgressRepository;

    @EventListener
    public void handlePlayerFinalBattleCreated(PlayerFinalBattleCreatedEvent event) {
        PlayerFinalBattle playerFinalBattle = event.getPlayerFinalBattle();

        Long playerId = playerFinalBattle.getPlayer().getId();
        Long levelId = playerFinalBattle.getObstacleOption().getObstacle().getFinalBattle().getLevel().getId();

        // Check if PlayerProgress already exists for this player and level
        Optional<PlayerProgress> existingProgress = playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

        if (existingProgress.isEmpty()) {
            // If no PlayerProgress exists, create a new one
            playerProgressCommandService.handle(
                    new CreatePlayerProgressCommand(playerId, levelId, false, 0, LocalDateTime.now())
            );
        }

        // Recalculate the player's progress
        playerProgressCommandService.handle(
                new RecalculatePlayerProgressCommand(playerId, levelId)
        );
    }

    @EventListener
    public void handlePlayerFinalBattleUpdated(PlayerFinalBattleUpdatedEvent event) {
        PlayerFinalBattle playerFinalBattle = event.getPlayerFinalBattle();

        Long playerId = playerFinalBattle.getPlayer().getId();
        Long levelId = playerFinalBattle.getObstacleOption().getObstacle().getFinalBattle().getLevel().getId();

        // Recalculate the player's progress after the final battle is updated
        playerProgressCommandService.handle(
                new RecalculatePlayerProgressCommand(playerId, levelId)
        );
    }
}