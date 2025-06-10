package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
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
public class PlayerMatchingPairEventListener {

    private final PlayerProgressCommandService playerProgressCommandService;
    private final PlayerProgressRepository playerProgressRepository;

    @EventListener
    public void handlePlayerMatchingPairCreated(PlayerMatchingPairCreatedEvent event) {
        PlayerMatchingPair playerMatchingPair = event.getPlayerMatchingPair();
        PlayerProgress currentProgress = playerMatchingPair.getPlayerProgress();

        Long playerId = currentProgress.getPlayer().getId();
        Long levelId = currentProgress.getLevel().getId();

        Optional<PlayerProgress> existingProgressOpt = playerProgressRepository.findByPlayerIdAndLevelId(playerId, levelId);

        MatchingItem itemA = playerMatchingPair.getMatchingItemA();
        MatchingItem itemB = playerMatchingPair.getMatchingItemB();

        boolean bothHavePairs = itemA.getMatchingPair() != null && itemB.getMatchingPair() != null;
        boolean pairsMatch = bothHavePairs && itemA.getMatchingPair().getId().equals(itemB.getMatchingPair().getId());

        int scoreToAdd = pairsMatch ? 10 : 0;

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
                            false,
                            scoreToAdd,
                            LocalDateTime.now()
                    )
            );
        }
    }
}
