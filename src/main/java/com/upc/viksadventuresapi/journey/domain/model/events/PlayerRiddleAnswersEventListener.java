package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
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
public class PlayerRiddleAnswersEventListener {

    private final PlayerProgressCommandService playerProgressCommandService;
    private final PlayerProgressRepository playerProgressRepository;

    @EventListener
    public void handlePlayerRiddleAnswerCreated(PlayerRiddleAnswerCreatedEvent event) {
        PlayerRiddleAnswer playerRiddleAnswer = event.getPlayerRiddleAnswer();
        PlayerProgress currentProgress = playerRiddleAnswer.getPlayerProgress();

        Long playerId = currentProgress.getPlayer().getId();
        Long levelId = currentProgress.getLevel().getId();

        Optional<PlayerProgress> existingProgressOpt = playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

        String entered = playerRiddleAnswer.getEnteredAnswer().enteredAnswer().trim().toLowerCase();
        String correct = playerRiddleAnswer.getRiddleDetail().getAnswer().answer().trim().toLowerCase();

        int scoreToAdd = entered.equals(correct) ? 10 : 0;

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