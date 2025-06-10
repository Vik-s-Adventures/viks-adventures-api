package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LevelRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.services.PlayerProgressCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerProgressRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerProgressCommandServiceImpl implements PlayerProgressCommandService {
    private final PlayerProgressRepository playerProgressRepository;
    private final PlayerRepository playerRepository;
    private final LevelRepository levelRepository;

    @Override
    public Optional<PlayerProgress> handle(CreatePlayerProgressCommand command) {
        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player with ID " + command.playerId() + " does not exist."));

        Level level = levelRepository.findById(command.levelId())
                .orElseThrow(() -> new IllegalArgumentException("Level with ID " + command.levelId() + " does not exist."));

        PlayerProgress playerProgress = new PlayerProgress(player, level, command);

        try {
            playerProgressRepository.save(playerProgress);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving player progress: " + e.getMessage(), e);
        }

        return Optional.of(playerProgress);
    }

    @Override
    public Optional<PlayerProgress> handle(UpdatePlayerProgressCommand command, Long playerProgressId) {
        Optional<PlayerProgress> optionalPlayerProgress = playerProgressRepository.findById(playerProgressId);
        if (optionalPlayerProgress.isEmpty()) {
            throw new IllegalArgumentException("Player progress with ID " + playerProgressId + " does not exist.");
        }
        PlayerProgress playerProgress = optionalPlayerProgress.get();
        playerProgress.setCompleted(true);
        playerProgress.setScore(playerProgress.getScore() + 1);
        playerProgress.setLastAccessed(command.lastAccessed());

        try {
            playerProgressRepository.save(playerProgress);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating player progress: " + e.getMessage(), e);
        }
        return Optional.of(playerProgress);

    }

    @Override
    public void handle(DeletePlayerProgressCommand command) {
        if (!playerProgressRepository.existsById(command.playerProgressId())) {
            throw new IllegalArgumentException("Player progress with ID " + command.playerProgressId() + " does not exist.");
        }
        try {
            playerProgressRepository.deleteById(command.playerProgressId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting player progress with ID " + command.playerProgressId() + ": " + e.getMessage());
        }
    }
}
