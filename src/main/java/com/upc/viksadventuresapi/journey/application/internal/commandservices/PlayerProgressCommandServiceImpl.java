package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.*;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.*;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.*;
import com.upc.viksadventuresapi.journey.domain.model.commands.*;
import com.upc.viksadventuresapi.journey.domain.services.PlayerCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerProgressCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerProgressCommandServiceImpl implements PlayerProgressCommandService {
    private final PlayerProgressRepository playerProgressRepository;
    private final PlayerRepository playerRepository;

    private final PlayerTomesReviewedRepository playerTomesReviewedRepository;
    private final PlayerFinalBattleRepository playerFinalBattleRepository;
    private final PlayerLinkingPairRepository playerLinkingPairRepository;
    private final PlayerMatchingPairRepository playerMatchingPairRepository;
    private final PlayerRiddleAnswerRepository playerRiddleAnswerRepository;

    private final ObstacleRepository obstacleRepository;
    private final LinkingRepository linkingRepository;
    private final LinkingPairRepository linkingPairRepository;
    private final MatchingRepository matchingRepository;
    private final MatchingPairRepository matchingPairRepository;
    private final RiddleRepository riddleRepository;
    private final RiddleDetailRepository riddleDetailRepository;
    private final LevelRepository levelRepository;
    private final ConceptRepository conceptRepository;
    private final TomeRepository tomeRepository;

    private final PlayerCommandService playerCommandService;

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
    public Optional<PlayerProgress> handle(RecalculatePlayerProgressCommand command) {
        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player with ID " + command.playerId() + " does not exist."));
        Level level = levelRepository.findById(command.levelId())
                .orElseThrow(() -> new IllegalArgumentException("Level with ID " + command.levelId() + " does not exist."));

        int totalScore = 0;

        // Tomes
        List<Tome> tomes = tomeRepository.findByLevelId(level.getId());
        for (Tome tome : tomes) {
            Optional<Concept> lastConceptOpt = conceptRepository.findTopByTomeOrderByIdDesc(tome);
            if (lastConceptOpt.isPresent()) {
                boolean reviewed = playerTomesReviewedRepository.existsByPlayerAndConcept(player, lastConceptOpt.get());
                if (reviewed) totalScore += 20;
            }
        }

        // FinalBattle
        List<Obstacle> obstacles = obstacleRepository.findByFinalBattleLevelId(level.getId());
        List<PlayerFinalBattle> playerBattles = playerFinalBattleRepository.findAllByPlayerIdAndObstacleOptionObstacleFinalBattleLevelId(player.getId(), level.getId());

        boolean allFinalBattleCorrect = obstacles.stream().allMatch(obstacle ->
                playerBattles.stream().anyMatch(pfb ->
                        pfb.getObstacleOption().getObstacle().getId().equals(obstacle.getId()) &&
                                pfb.getObstacleOption().getIsCorrect()
                )
        );
        if (!obstacles.isEmpty() && allFinalBattleCorrect) totalScore += 20;

        // Linking
        List<Linking> links = linkingRepository.findByTrialLevelId(level.getId());
        for (Linking linking : links) {
            List<LinkingPair> linkingPairs = linkingPairRepository.findByLinking(linking);
            List<PlayerLinkingPair> playerLinkings = playerLinkingPairRepository.findAllByPlayerIdAndLinkingPairAnswerLinkingId(player.getId(), linking.getId());

            boolean allLinkingCorrect = linkingPairs.stream().allMatch(pair ->
                    playerLinkings.stream().anyMatch(plp ->
                            plp.getLinkingPairImage().getId().equals(pair.getId()) &&
                                    plp.getLinkingPairAnswer().getId().equals(pair.getId())
                    )
            );
            if (!linkingPairs.isEmpty() && allLinkingCorrect) totalScore += 20;
        }

        // Matching
        List<Matching> matches = matchingRepository.findByTrialLevelId(level.getId());
        for (Matching matching : matches) {
            List<MatchingPair> matchingPairs = matchingPairRepository.findByMatching(matching);
            List<PlayerMatchingPair> playerMatchings = playerMatchingPairRepository.findAllByPlayerIdAndMatchingItemA_MatchingId(player.getId(), matching.getId());

            boolean allMatchingCorrect = matchingPairs.stream().allMatch(pair ->
                    playerMatchings.stream().anyMatch(pmp -> {
                        MatchingItem a = pmp.getMatchingItemA();
                        MatchingItem b = pmp.getMatchingItemB();
                        return a.getMatchingPair() != null &&
                                b.getMatchingPair() != null &&
                                a.getMatchingPair().getId().equals(pair.getId()) &&
                                b.getMatchingPair().getId().equals(pair.getId());
                    })
            );
            if (!matchingPairs.isEmpty() && allMatchingCorrect) totalScore += 20;
        }

        // Riddle
        List<Riddle> riddles = riddleRepository.findByTrialLevelId(level.getId());
        for (Riddle riddle : riddles) {
            List<RiddleDetail> details = riddleDetailRepository.findByRiddle(riddle);
            List<PlayerRiddleAnswer> answers = playerRiddleAnswerRepository.findAllByPlayerIdAndRiddleDetailRiddleId(player.getId(), riddle.getId());

            boolean allRiddlesCorrect = details.stream().allMatch(detail ->
                    answers.stream().anyMatch(ans ->
                            ans.getRiddleDetail().getId().equals(detail.getId()) &&
                                    ans.getEnteredAnswer().enteredAnswer().trim().equalsIgnoreCase(detail.getAnswer().answer().trim())
                    )
            );
            if (!details.isEmpty() && allRiddlesCorrect) totalScore += 20;
        }

        boolean completed = totalScore >= 100;

        Optional<PlayerProgress> existingProgressOpt = playerProgressRepository.findByPlayerIdAndLevelId(player.getId(), level.getId());
        PlayerProgress progress;
        if (existingProgressOpt.isPresent()) {
            progress = existingProgressOpt.get();
            progress.setScore(totalScore);
            progress.setCompleted(completed);
            progress.setLastAccessed(LocalDateTime.now());
        } else {
            progress = new PlayerProgress(player, level,
                    new CreatePlayerProgressCommand(player.getId(), level.getId(), completed, totalScore, LocalDateTime.now()));
        }

        playerProgressRepository.save(progress);
        playerCommandService.handle(new UpdatePlayerTotalScoreCommand(player.getId()));

        return Optional.of(progress);
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