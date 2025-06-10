package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerProgressCommand;
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

        Long playerId = playerFinalBattle.getPlayerProgress().getPlayer().getId();
        Long levelId = playerFinalBattle.getPlayerProgress().getLevel().getId();
        LocalDateTime now = LocalDateTime.now();

        Boolean isCorrect = playerFinalBattle.getObstacleOption().getIsCorrect();
        int scoreToAdd = isCorrect ? 20 : 0;

        Optional<PlayerProgress> existingProgress = playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

        if (existingProgress.isPresent()) {
            PlayerProgress progress = existingProgress.get();
            int updatedScore = progress.getScore() + scoreToAdd;
            playerProgressCommandService.handle(
                    new UpdatePlayerProgressCommand(false, updatedScore, now),
                    progress.getId()
            );
        } else {
            playerProgressCommandService.handle(
                    new CreatePlayerProgressCommand(playerId, levelId, false, scoreToAdd, now)
            );
        }
    }
}