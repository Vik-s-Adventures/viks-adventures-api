package com.upc.viksadventuresapi.journey.domain.model.events;

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
public class PlayerMatchingPairEventListener {

    private final PlayerProgressCommandService playerProgressCommandService;
    private final PlayerProgressRepository playerProgressRepository;

    @EventListener
    public void handlePlayerMatchingPairCreated(PlayerMatchingPairCreatedEvent event) {

        Long playerId = event.getPlayer().getId();
        Long levelId = event.getMatching().getTrial().getLevel().getId();

        Optional<PlayerProgress> existingProgressOpt = playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

        if (existingProgressOpt.isEmpty()) {
            playerProgressCommandService.handle(
                    new CreatePlayerProgressCommand(playerId, levelId, false, 0, LocalDateTime.now())
            );
        }

        playerProgressCommandService.handle(new RecalculatePlayerProgressCommand(playerId, levelId));
    }
}