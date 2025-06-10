package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.RiddleDetailRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.events.PlayerRiddleAnswerCreatedEvent;
import com.upc.viksadventuresapi.journey.domain.services.PlayerRiddleAnswerCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerProgressRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRiddleAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerRiddleAnswerCommandServiceImpl implements PlayerRiddleAnswerCommandService {
    private final PlayerRiddleAnswerRepository playerRiddleAnswerRepository;
    private final PlayerProgressRepository playerProgressRepository;
    private final RiddleDetailRepository riddleDetailRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Optional<PlayerRiddleAnswer> handle(CreatePlayerRiddleAnswerCommand command) {
        PlayerProgress playerProgress = playerProgressRepository.findById(command.playerProgressId())
                .orElseThrow(() -> new IllegalArgumentException("PlayerProgress with ID " + command.playerProgressId() + " does not exist."));

        RiddleDetail riddleDetail = riddleDetailRepository.findById(command.riddleDetailId())
                .orElseThrow(() -> new IllegalArgumentException("RiddleDetail with ID " + command.riddleDetailId() + " does not exist."));

        PlayerRiddleAnswer playerRiddleAnswer = new PlayerRiddleAnswer(playerProgress, riddleDetail, command);

        try {
            playerRiddleAnswerRepository.save(playerRiddleAnswer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving PlayerRiddleAnswer: " + e.getMessage(), e);
        }

        // Publish event after successful save
        eventPublisher.publishEvent(new PlayerRiddleAnswerCreatedEvent(this, playerRiddleAnswer));
        return Optional.of(playerRiddleAnswer);
    }

    @Override
    public void handle(DeletePlayerRiddleAnswerCommand command) {
        if (!playerRiddleAnswerRepository.existsById(command.playerRiddleAnswerId())) {
            throw new IllegalArgumentException("PlayerRiddleAnswer with ID " + command.playerRiddleAnswerId() + " does not exist.");
        }

        try {
            playerRiddleAnswerRepository.deleteById(command.playerRiddleAnswerId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting PlayerRiddleAnswer with ID " + command.playerRiddleAnswerId() + ": " + e.getMessage());
        }
    }
}
