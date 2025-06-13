package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
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
public class PlayerRiddleAnswersEventListener {

    private final PlayerProgressCommandService playerProgressCommandService;
    private final PlayerProgressRepository playerProgressRepository;

    @EventListener
    public void handlePlayerRiddleAnswerCreated(PlayerRiddleAnswerCreatedEvent event) {
        PlayerRiddleAnswer playerRiddleAnswer = event.getPlayerRiddleAnswer();

        Long playerId = playerRiddleAnswer.getPlayer().getId();
        Long levelId = playerRiddleAnswer.getRiddleDetail().getRiddle().getTrial().getLevel().getId();

        // Verify if the player progress already exists for this level
        Optional<PlayerProgress> existingProgressOpt = playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

        if (existingProgressOpt.isEmpty()) {
            // If not, create a new player progress entry
            playerProgressCommandService.handle(
                    new CreatePlayerProgressCommand(playerId, levelId, false, 0, LocalDateTime.now())
            );
        }

        // Recalculate the player progress based on the riddle answer
        playerProgressCommandService.handle(
                new RecalculatePlayerProgressCommand(playerId, levelId)
        );
    }
}