package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ConceptRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.model.events.PlayerTomesReviewedCreatedEvent;
import com.upc.viksadventuresapi.journey.domain.model.events.PlayerTomesReviewedUpdatedEvent;
import com.upc.viksadventuresapi.journey.domain.services.PlayerTomesReviewedCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerTomesReviewedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerTomesReviewedCommandServiceImpl implements PlayerTomesReviewedCommandService {
    private final PlayerTomesReviewedRepository playerTomesReviewedRepository;
    private final PlayerRepository playerRepository;
    private final ConceptRepository conceptRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Optional<PlayerTomesReviewed> handle(CreatePlayerTomesReviewedCommand command) {
        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player progress with ID " + command.playerId() + " does not exist."));

        Concept concept = conceptRepository.findById(command.conceptId())
                .orElseThrow(() -> new IllegalArgumentException("Concept with ID " + command.conceptId() + " does not exist."));

        var playerTomesReviewed = new PlayerTomesReviewed(player, concept);

        try {
            playerTomesReviewedRepository.save(playerTomesReviewed);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving player tomes reviewed: " + e.getMessage(), e);
        }

        // Publish event after saving
        eventPublisher.publishEvent(new PlayerTomesReviewedCreatedEvent(this, playerTomesReviewed));

        return Optional.of(playerTomesReviewed);
    }

    @Override
    public Optional<PlayerTomesReviewed> handle(UpdatePlayerTomesReviewedCommand command) {
        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player progress with ID " + command.playerId() + " does not exist."));

        Concept newConcept = conceptRepository.findById(command.conceptId())
                .orElseThrow(() -> new IllegalArgumentException("Concept with ID " + command.conceptId() + " does not exist."));

        Tome tome = newConcept.getTome();

        PlayerTomesReviewed existing = playerTomesReviewedRepository
                .findByPlayerAndConcept_Tome(player, tome)
                .orElseThrow(() -> new IllegalArgumentException("No PlayerTomesReviewed found for this progress and tome."));

        // Actualiza el concept (si no es el mismo)
        if (!existing.getConcept().getId().equals(newConcept.getId())) {
            existing.setConcept(newConcept);
            playerTomesReviewedRepository.save(existing);
            eventPublisher.publishEvent(new PlayerTomesReviewedUpdatedEvent(this, existing));
        }

        return Optional.of(existing);
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
