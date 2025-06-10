package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ConceptRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
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
public class PlayerTomesReviewedEventListener {
    private final PlayerProgressCommandService playerProgressCommandService;
    private final PlayerProgressRepository playerProgressRepository;
    private final ConceptRepository conceptRepository;

    @EventListener
    public void handlePlayerTomesReviewedCreated(PlayerTomesReviewedCreatedEvent event) {
        handleReviewEvent(event.getPlayerTomesReviewed());
    }

    @EventListener
    public void handlePlayerTomesReviewedUpdated(PlayerTomesReviewedUpdatedEvent event) {
        handleReviewEvent(event.getPlayerTomesReviewed());
    }

    private void handleReviewEvent(PlayerTomesReviewed reviewed) {
        Concept reviewedConcept = reviewed.getConcept();
        Tome tome = reviewedConcept.getTome();
        PlayerProgress progress = reviewed.getPlayerProgress();

        Long playerId = progress.getPlayer().getId();
        Long levelId = progress.getLevel().getId();

        // Verificar si es el último concept del tome
        Optional<Concept> lastConceptOpt = conceptRepository.findTopByTomeOrderByIdDesc(tome);

        int scoreToAdd = 0;
        if (lastConceptOpt.isPresent() &&
                lastConceptOpt.get().getId().equals(reviewedConcept.getId())) {
            scoreToAdd = 20;
        }

        Optional<PlayerProgress> existingProgressOpt =
                playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

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