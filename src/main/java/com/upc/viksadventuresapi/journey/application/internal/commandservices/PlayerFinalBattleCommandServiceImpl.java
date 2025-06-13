package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ObstacleOptionRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.domain.model.events.PlayerFinalBattleCreatedEvent;
import com.upc.viksadventuresapi.journey.domain.model.events.PlayerFinalBattleUpdatedEvent;
import com.upc.viksadventuresapi.journey.domain.services.PlayerFinalBattleCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerFinalBattleRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerFinalBattleCommandServiceImpl implements PlayerFinalBattleCommandService {
    private final PlayerFinalBattleRepository playerFinalBattleRepository;
    private final PlayerRepository playerRepository;
    private final ObstacleOptionRepository obstacleOptionRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Optional<PlayerFinalBattle> handle(CreatePlayerFinalBattleCommand command) {
        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("PlayerProgress with ID " + command.playerId() + " does not exist."));

        ObstacleOption obstacleOption = obstacleOptionRepository.findById(command.obstacleOptionId())
                .orElseThrow(() -> new IllegalArgumentException("ObstacleOption with ID " + command.obstacleOptionId() + " does not exist."));

        PlayerFinalBattle playerFinalBattle = new PlayerFinalBattle(player, obstacleOption);

        try {
            playerFinalBattleRepository.save(playerFinalBattle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving PlayerFinalBattle: " + e.getMessage(), e);
        }

        // Publish the event after saving
        eventPublisher.publishEvent(new PlayerFinalBattleCreatedEvent(this, playerFinalBattle));

        return Optional.of(playerFinalBattle);
    }

    @Override
    public Optional<PlayerFinalBattle> handle(UpdatePlayerFinalBattleCommand command) {
        PlayerFinalBattle existing = playerFinalBattleRepository.findByPlayerIdAndObstacleOptionObstacleId(
                        command.playerId(), command.obstacleId())
                .orElseThrow(() -> new IllegalArgumentException("No PlayerFinalBattle found for this player and obstacle."));

        ObstacleOption newObstacleOption = obstacleOptionRepository.findById(command.newObstacleOptionId())
                .orElseThrow(() -> new IllegalArgumentException("ObstacleOption with ID " + command.newObstacleOptionId() + " does not exist."));

        if (!existing.getObstacleOption().getId().equals(newObstacleOption.getId())) {
            existing.setObstacleOption(newObstacleOption);
            playerFinalBattleRepository.save(existing);
            eventPublisher.publishEvent(new PlayerFinalBattleUpdatedEvent(this, existing));
        }

        return Optional.of(existing);
    }

    @Override
    public void handle(DeletePlayerFinalBattleCommand command) {
        if (!playerFinalBattleRepository.existsById(command.playerFinalBattleId())) {
            throw new IllegalArgumentException("PlayerFinalBattle with ID " + command.playerFinalBattleId() + " does not exist.");
        }
        try {
            playerFinalBattleRepository.deleteById(command.playerFinalBattleId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting PlayerFinalBattle with ID " + command.playerFinalBattleId());
        }
    }
}
