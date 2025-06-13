package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingPairRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerLinkingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.SavePlayerLinkingResponseCommand;
import com.upc.viksadventuresapi.journey.domain.model.events.PlayerLinkingPairCreatedEvent;
import com.upc.viksadventuresapi.journey.domain.services.PlayerLinkingPairCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerLinkingPairRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerLinkingPairCommandServiceImpl implements PlayerLinkingPairCommandService {
    private final PlayerLinkingPairRepository playerLinkingPairRepository;
    private final PlayerRepository playerRepository;
    private final LinkingRepository linkingRepository;
    private final LinkingPairRepository linkingPairRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void handle(SavePlayerLinkingResponseCommand command) {

        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        Linking linking = linkingRepository.findById(command.linkingId())
                .orElseThrow(() -> new IllegalArgumentException("Linking not found"));

        // Delete existing PlayerLinkingPairs for the player and linking
        List<PlayerLinkingPair> existingPairs = playerLinkingPairRepository.findAllByPlayerIdAndLinkingPairAnswerLinkingId(player.getId(), linking.getId());
        playerLinkingPairRepository.deleteAll(existingPairs);

        // Create new PlayerLinkingPairs
        List<PlayerLinkingPair> newPairs = command.pairs().stream().map(req -> {
            LinkingPair linkingPairImage = linkingPairRepository.findById(req.linkingPairImageId())
                    .orElseThrow(() -> new IllegalArgumentException("LinkingPairImage not found"));
            LinkingPair linkingPairAnswer = linkingPairRepository.findById(req.linkingPairAnswerId())
                    .orElseThrow(() -> new IllegalArgumentException("LinkingPairAnswer not found"));
            return new PlayerLinkingPair(player, linkingPairImage, linkingPairAnswer);
        }).toList();

        playerLinkingPairRepository.saveAll(newPairs);

        // Publish event for each new PlayerLinkingPair created
        eventPublisher.publishEvent(new PlayerLinkingPairCreatedEvent(this, player, linking));
    }

    @Override
    public void handle(DeletePlayerLinkingPairCommand command) {
        if (!playerLinkingPairRepository.existsById(command.playerLinkingPairId())) {
            throw new IllegalArgumentException("PlayerLinkingPair with ID " + command.playerLinkingPairId() + " does not exist.");
        }

        try {
            playerLinkingPairRepository.deleteById(command.playerLinkingPairId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting PlayerLinkingPair with ID " + command.playerLinkingPairId() + ": " + e.getMessage());
        }
    }
}
