package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
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
public class PlayerLinkingPairEventListener {
    private final PlayerProgressCommandService playerProgressCommandService;
    private final PlayerProgressRepository playerProgressRepository;

    @EventListener
    public void handlePlayerLinkingPairCreated(PlayerLinkingPairCreatedEvent event) {
        PlayerLinkingPair playerLinkingPair = event.getPlayerLinkingPair();
        PlayerProgress currentProgress = playerLinkingPair.getPlayerProgress();

        Long playerId = currentProgress.getPlayer().getId();
        Long levelId = currentProgress.getLevel().getId();

        Optional<PlayerProgress> existingProgressOpt = playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

        int scoreToAdd = playerLinkingPair.getLinkingPairImage().getId().equals(
                playerLinkingPair.getLinkingPairAnswer().getId()) ? 10 : 0;

        if (existingProgressOpt.isPresent()) {
            PlayerProgress existingProgress = existingProgressOpt.get();
            playerProgressCommandService.handle(
                    new UpdatePlayerProgressCommand(
                            true,
                            existingProgress.getScore() + scoreToAdd,
                            LocalDateTime.now()
                    ),
                    existingProgress.getId()
            );
        } else {
            playerProgressCommandService.handle(
                    new CreatePlayerProgressCommand(
                            playerId,
                            levelId,
                            true,
                            scoreToAdd,
                            LocalDateTime.now()
                    )
            );
        }
    }
}