package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ConceptRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.services.PlayerTomesReviewedCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerProgressRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerTomesReviewedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerTomesReviewedCommandServiceImpl implements PlayerTomesReviewedCommandService {
    private final PlayerTomesReviewedRepository playerTomesReviewedRepository;
    private final PlayerProgressRepository playerProgressRepository;
    private final ConceptRepository conceptRepository;

    @Override
    public Optional<PlayerTomesReviewed> handle(CreatePlayerTomesReviewedCommand command) {
        Optional<PlayerProgress> optionalPlayerProgress = playerProgressRepository.findById(command.playerProgressId());
        if (optionalPlayerProgress.isEmpty()) {
            throw new IllegalArgumentException("Player progress with ID " + command.playerProgressId() + " does not exist.");
        }

        Optional<Concept> optionalConcept = conceptRepository.findById(command.conceptId());
        if (optionalConcept.isEmpty()) {
            throw new IllegalArgumentException("Concept with ID " + command.conceptId() + " does not exist.");
        }

        PlayerProgress playerProgress = optionalPlayerProgress.get();
        Concept concept = optionalConcept.get();
        var playerTomesReviewed = new PlayerTomesReviewed(playerProgress, concept);

        try {
            playerTomesReviewedRepository.save(playerTomesReviewed);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving player tomes reviewed: " + e.getMessage());
        }

        return Optional.of(playerTomesReviewed);
    }

    @Override
    public void handle(DeletePlayerTomesReviewedCommand command) {
        if (!playerTomesReviewedRepository.existsById(command.playerTomesReviewedId())) {
            throw new IllegalArgumentException("Player tomes reviewed with ID " + command.playerTomesReviewedId() + " does not exist.");
        }
        try {
            playerTomesReviewedRepository.deleteById(command.playerTomesReviewedId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting player tomes reviewed with ID " + command.playerTomesReviewedId());
        }
    }
}
