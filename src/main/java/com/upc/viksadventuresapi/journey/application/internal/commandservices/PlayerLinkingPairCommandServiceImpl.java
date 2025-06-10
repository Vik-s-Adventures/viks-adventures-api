package com.upc.viksadventuresapi.journey.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingPairRepository;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerProgress;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerLinkingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerLinkingPairCommand;
import com.upc.viksadventuresapi.journey.domain.services.PlayerLinkingPairCommandService;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerLinkingPairRepository;
import com.upc.viksadventuresapi.journey.infrastructure.persistence.jpa.repositories.PlayerProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerLinkingPairCommandServiceImpl implements PlayerLinkingPairCommandService {
    private final PlayerLinkingPairRepository playerLinkingPairRepository;
    private final PlayerProgressRepository playerProgressRepository;
    private final LinkingPairRepository linkingPairRepository;

    @Override
    public Optional<PlayerLinkingPair> handle(CreatePlayerLinkingPairCommand command) {
        PlayerProgress playerProgress = playerProgressRepository.findById(command.playerProgressId())
                .orElseThrow(() -> new IllegalArgumentException("PlayerProgress with ID " + command.playerProgressId() + " does not exist."));

        LinkingPair linkingPairImage = linkingPairRepository.findById(command.linkingPairImageId())
                .orElseThrow(() -> new IllegalArgumentException("LinkingPair image ID does not exist."));

        LinkingPair linkingPairAnswer = linkingPairRepository.findById(command.linkingPairAnswerId())
                .orElseThrow(() -> new IllegalArgumentException("LinkingPair answer ID does not exist."));

        PlayerLinkingPair playerLinkingPair = new PlayerLinkingPair(playerProgress, linkingPairImage, linkingPairAnswer);

        try {
            playerLinkingPairRepository.save(playerLinkingPair);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving PlayerLinkingPair: " + e.getMessage(), e);
        }

        return Optional.of(playerLinkingPair);
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
