package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.RiddleDetailRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.events.PlayerRiddleAnswerCreatedEvent;
import com.upc.viksadventuresapi.journey.domain.model.valueobjects.EnteredAnswer;
import com.upc.viksadventuresapi.journey.domain.services.PlayerRiddleAnswerCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRiddleAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerRiddleAnswerCommandServiceImpl implements PlayerRiddleAnswerCommandService {

    private final PlayerRiddleAnswerRepository playerRiddleAnswerRepository;
    private final PlayerRepository playerRepository;
    private final RiddleDetailRepository riddleDetailRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Optional<PlayerRiddleAnswer> handle(CreatePlayerRiddleAnswerCommand command) {

        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player with ID " + command.playerId() + " does not exist."));

        RiddleDetail riddleDetail = riddleDetailRepository.findById(command.riddleDetailId())
                .orElseThrow(() -> new IllegalArgumentException("RiddleDetail with ID " + command.riddleDetailId() + " does not exist."));

        // Validar que no exista previamente
        if (playerRiddleAnswerRepository.existsByPlayerIdAndRiddleDetailId(player.getId(), riddleDetail.getId())) {
            throw new IllegalStateException("PlayerRiddleAnswer already exists.");
        }

        PlayerRiddleAnswer playerRiddleAnswer = new PlayerRiddleAnswer(player, riddleDetail, command);

        playerRiddleAnswerRepository.save(playerRiddleAnswer);
        eventPublisher.publishEvent(new PlayerRiddleAnswerCreatedEvent(this, playerRiddleAnswer));

        return Optional.of(playerRiddleAnswer);
    }

    @Override
    public Optional<PlayerRiddleAnswer> handle(UpdatePlayerRiddleAnswerCommand command) {

        PlayerRiddleAnswer playerRiddleAnswer = playerRiddleAnswerRepository
                .findByPlayerIdAndRiddleDetailId(command.playerId(), command.riddleDetailId())
                .orElseThrow(() -> new IllegalArgumentException("PlayerRiddleAnswer does not exist to update."));

        playerRiddleAnswer.setEnteredAnswer(new EnteredAnswer(command.enteredAnswer()));

        playerRiddleAnswerRepository.save(playerRiddleAnswer);
        eventPublisher.publishEvent(new PlayerRiddleAnswerCreatedEvent(this, playerRiddleAnswer));

        return Optional.of(playerRiddleAnswer);
    }

    @Override
    public void handle(DeletePlayerRiddleAnswerCommand command) {
        if (!playerRiddleAnswerRepository.existsById(command.playerRiddleAnswerId())) {
            throw new IllegalArgumentException("PlayerRiddleAnswer with ID " + command.playerRiddleAnswerId() + " does not exist.");
        }
        playerRiddleAnswerRepository.deleteById(command.playerRiddleAnswerId());
    }
}