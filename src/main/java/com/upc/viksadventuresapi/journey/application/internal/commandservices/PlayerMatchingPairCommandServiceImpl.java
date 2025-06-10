package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingItemRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerMatchingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerMatchingPairCommand;
import com.upc.viksadventuresapi.journey.domain.services.PlayerMatchingPairCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerMatchingPairRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerMatchingPairCommandServiceImpl implements PlayerMatchingPairCommandService {
    private final PlayerMatchingPairRepository playerMatchingPairRepository;
    private final PlayerProgressRepository playerProgressRepository;
    private final MatchingItemRepository matchingItemRepository;

    @Override
    public Optional<PlayerMatchingPair> handle(CreatePlayerMatchingPairCommand command) {
        PlayerProgress playerProgress = playerProgressRepository.findById(command.playerProgressId())
                .orElseThrow(() -> new IllegalArgumentException("PlayerProgress with ID " + command.playerProgressId() + " does not exist."));

        MatchingItem matchingItemA = matchingItemRepository.findById(command.matchingItemA())
                .orElseThrow(() -> new IllegalArgumentException("MatchingItem A with ID " + command.matchingItemA() + " does not exist."));

        MatchingItem matchingItemB = matchingItemRepository.findById(command.matchingItemB())
                .orElseThrow(() -> new IllegalArgumentException("MatchingItem B with ID " + command.matchingItemB() + " does not exist."));

        if (matchingItemA.getId().equals(matchingItemB.getId())) {
            throw new IllegalArgumentException("Matching items cannot be the same.");
        }

        PlayerMatchingPair playerMatchingPair = new PlayerMatchingPair(playerProgress, matchingItemA, matchingItemB);

        try {
            playerMatchingPairRepository.save(playerMatchingPair);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving PlayerMatchingPair: " + e.getMessage(), e);
        }

        return Optional.of(playerMatchingPair);
    }

    @Override
    public void handle(DeletePlayerMatchingPairCommand command) {
        if (!playerMatchingPairRepository.existsById(command.playerMatchingPairId())) {
            throw new IllegalArgumentException("PlayerMatchingPair with ID " + command.playerMatchingPairId() + " does not exist.");
        }
        try {
            playerMatchingPairRepository.deleteById(command.playerMatchingPairId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting PlayerMatchingPair with ID " + command.playerMatchingPairId() + ": " + e.getMessage());
        }
    }
}
