package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingItemRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerMatchingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.SavePlayerMatchingResponseCommand;
import com.upc.viksadventuresapi.journey.domain.model.events.PlayerMatchingPairCreatedEvent;
import com.upc.viksadventuresapi.journey.domain.services.PlayerMatchingPairCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerMatchingPairRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerMatchingPairCommandServiceImpl implements PlayerMatchingPairCommandService {
    private final PlayerMatchingPairRepository playerMatchingPairRepository;
    private final PlayerRepository playerRepository;
    private final MatchingItemRepository matchingItemRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void handle(SavePlayerMatchingResponseCommand command) {
        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        // Get the MatchingItem from the first pair
        if (command.pairs().isEmpty()) {
            throw new IllegalArgumentException("No pairs provided.");
        }

        Long matchingItemAId = command.pairs().get(0).matchingItemAId();
        MatchingItem itemA = matchingItemRepository.findById(matchingItemAId)
                .orElseThrow(() -> new IllegalArgumentException("MatchingItem A not found"));

        Matching matching = itemA.getMatching();

        // Delete existing pairs for the player and matching
        List<PlayerMatchingPair> existingPairs = playerMatchingPairRepository
                .findAllByPlayerIdAndMatchingItemA_MatchingId(player.getId(), matching.getId());
        playerMatchingPairRepository.deleteAll(existingPairs);

        // Save the new pairs
        List<PlayerMatchingPair> newPairs = command.pairs().stream().map(req -> {
            MatchingItem itemAObj = matchingItemRepository.findById(req.matchingItemAId())
                    .orElseThrow(() -> new IllegalArgumentException("MatchingItem A not found"));
            MatchingItem itemB = matchingItemRepository.findById(req.matchingItemBId())
                    .orElseThrow(() -> new IllegalArgumentException("MatchingItem B not found"));

            if (itemAObj.getId().equals(itemB.getId())) {
                throw new IllegalArgumentException("Items cannot be paired with themselves");
            }

            return new PlayerMatchingPair(player, itemAObj, itemB);
        }).toList();

        playerMatchingPairRepository.saveAll(newPairs);

        // Publish the event
        eventPublisher.publishEvent(new PlayerMatchingPairCreatedEvent(this, player, matching));
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
