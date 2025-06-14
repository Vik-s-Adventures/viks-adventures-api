package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingPairRepository;
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
    private final LinkingPairRepository linkingPairRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    @Override
    public void handle(SavePlayerLinkingResponseCommand command) {
        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        // Get the LinkingPairImage from the first pair
        if (command.pairs().isEmpty()) {
            throw new IllegalArgumentException("No pairs provided.");
        }

        Long linkingPairImageId = command.pairs().get(0).linkingPairImageId();
        LinkingPair linkingPairImage = linkingPairRepository.findById(linkingPairImageId)
                .orElseThrow(() -> new IllegalArgumentException("LinkingPairImage not found"));

        Linking linking = linkingPairImage.getLinking();

        // Verify if the linking exists
        List<PlayerLinkingPair> existingPairs = playerLinkingPairRepository
                .findAllByPlayerIdAndLinkingPairAnswerLinkingId(player.getId(), linking.getId());
        playerLinkingPairRepository.deleteAll(existingPairs);

        // Save the new pairs
        List<PlayerLinkingPair> newPairs = command.pairs().stream().map(req -> {
            LinkingPair image = linkingPairRepository.findById(req.linkingPairImageId())
                    .orElseThrow(() -> new IllegalArgumentException("LinkingPairImage not found"));
            LinkingPair answer = linkingPairRepository.findById(req.linkingPairAnswerId())
                    .orElseThrow(() -> new IllegalArgumentException("LinkingPairAnswer not found"));
            return new PlayerLinkingPair(player, image, answer);
        }).toList();

        playerLinkingPairRepository.saveAll(newPairs);

        // Update player progress
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
