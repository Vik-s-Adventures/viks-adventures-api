package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.LinkingPair;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLinkingPairCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLinkingPairCommand;
import com.upc.viksadventuresapi.adventure.domain.services.LinkingPairCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingPairRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LinkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkingPairCommandServiceImpl implements LinkingPairCommandService {
    private final LinkingRepository linkingRepository;
    private final LinkingPairRepository linkingPairRepository;

    @Override
    public Optional<LinkingPair> handle(CreateLinkingPairCommand command) {
        Optional<Linking> optionalLinking = linkingRepository.findById(command.linkingId());

        if (optionalLinking.isEmpty()) {
            throw new IllegalArgumentException("Linking with ID " + command.linkingId() + " does not exist.");
        }

        Linking linking = optionalLinking.get();
        var linkingPair = new LinkingPair(linking, command);

        try {
            linkingPairRepository.save(linkingPair);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving linking pair: " + e.getMessage());
        }

        return Optional.of(linkingPair);
    }

    @Override
    public void handle(DeleteLinkingPairCommand command) {
        if (!linkingPairRepository.existsById(command.linkingPairId())) {
            throw new IllegalArgumentException("LinkingPair with ID " + command.linkingPairId() + " does not exist.");
        }
        try {
            linkingPairRepository.deleteById(command.linkingPairId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting linking pair with ID " + command.linkingPairId());
        }
    }
}
