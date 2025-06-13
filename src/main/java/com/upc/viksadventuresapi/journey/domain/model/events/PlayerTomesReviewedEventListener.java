package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
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
public class PlayerTomesReviewedEventListener {
    private final PlayerProgressCommandService playerProgressCommandService;
    private final PlayerProgressRepository playerProgressRepository;

    @EventListener
    public void handlePlayerTomesReviewedCreated(PlayerTomesReviewedCreatedEvent event) {
        handleReviewEvent(event.getPlayerTomesReviewed());
    }

    @EventListener
    public void handlePlayerTomesReviewedUpdated(PlayerTomesReviewedUpdatedEvent event) {
        handleReviewEvent(event.getPlayerTomesReviewed());
    }

    private void handleReviewEvent(PlayerTomesReviewed reviewed) {
        Long playerId = reviewed.getPlayer().getId();
        Long levelId = reviewed.getConcept().getTome().getLevel().getId();

        // Verify if a PlayerProgress already exists for the player and level
        Optional<PlayerProgress> existingProgressOpt = playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

        if (existingProgressOpt.isEmpty()) {
            // If no existing progress, create a new PlayerProgress
            playerProgressCommandService.handle(
                    new CreatePlayerProgressCommand(playerId, levelId, false, 0, LocalDateTime.now())
            );
        }

        playerProgressCommandService.handle(
                // Recalculate the PlayerProgress for the player and level
                new RecalculatePlayerProgressCommand(playerId, levelId)
        );
    }
}