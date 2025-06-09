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
        Optional<PlayerProgress> optionalPlayerProgress = playerProgressRepository.findById(command.playerProgressId());

        if (optionalPlayerProgress.isEmpty()) {
            throw new IllegalArgumentException("PlayerProgress with ID " + command.playerProgressId() + " does not exist.");
        }

        Optional<MatchingItem> optionalMatchingItemA = matchingItemRepository.findById(command.matchingItemA());
        Optional<MatchingItem> optionalMatchingItemB = matchingItemRepository.findById(command.matchingItemB());
        if (optionalMatchingItemA.isEmpty() || optionalMatchingItemB.isEmpty()) {
            throw new IllegalArgumentException("One or both MatchingItems do not exist.");
        }
        if (optionalMatchingItemA.get().getId().equals(optionalMatchingItemB.get().getId()) ) {
            throw new IllegalArgumentException("Matching items cannot be the same.");
        }

        PlayerProgress playerProgress = optionalPlayerProgress.get();
        MatchingItem matchingItemA = optionalMatchingItemA.get();
        MatchingItem matchingItemB = optionalMatchingItemB.get();
        var playerMatchingPair = new PlayerMatchingPair(playerProgress, matchingItemA, matchingItemB, command);

        try {
            playerMatchingPairRepository.save(playerMatchingPair);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving PlayerMatchingPair: " + e.getMessage());
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
