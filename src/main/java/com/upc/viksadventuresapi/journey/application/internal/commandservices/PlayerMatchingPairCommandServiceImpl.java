package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingItemRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingRepository;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerMatchingPairCommandServiceImpl implements PlayerMatchingPairCommandService {
    private final PlayerMatchingPairRepository playerMatchingPairRepository;
    private final PlayerRepository playerRepository;
    private final MatchingItemRepository matchingItemRepository;
    private final MatchingRepository matchingRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void handle(SavePlayerMatchingResponseCommand command) {

        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        Matching matching = matchingRepository.findById(command.matchingId())
                .orElseThrow(() -> new IllegalArgumentException("Matching not found"));

        // Eliminar respuestas previas para este player + matching
        List<PlayerMatchingPair> existingPairs =
                playerMatchingPairRepository.findAllByPlayerIdAndMatchingItemA_MatchingId(player.getId(), matching.getId());
        playerMatchingPairRepository.deleteAll(existingPairs);

        // Validar y crear nuevos pares
        List<PlayerMatchingPair> newPairs = command.pairs().stream().map(req -> {
            MatchingItem itemA = matchingItemRepository.findById(req.matchingItemAId())
                    .orElseThrow(() -> new IllegalArgumentException("MatchingItem A not found"));
            MatchingItem itemB = matchingItemRepository.findById(req.matchingItemBId())
                    .orElseThrow(() -> new IllegalArgumentException("MatchingItem B not found"));

            if (itemA.getId().equals(itemB.getId())) {
                throw new IllegalArgumentException("Items cannot be paired with themselves");
            }

            return new PlayerMatchingPair(player, itemA, itemB);
        }).toList();

        playerMatchingPairRepository.saveAll(newPairs);

        // Publicar evento para recalcular progreso
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
