package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingPair;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingItemCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingItemCommand;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingItemCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingItemRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingItemCommandServiceImpl implements MatchingItemCommandService {
    private final MatchingPairRepository matchingPairRepository;
    private final MatchingItemRepository matchingItemRepository;

    @Override
    public Optional<MatchingItem> handle(CreateMatchingItemCommand command) {
        Optional<MatchingPair> optionalMatchingPair = matchingPairRepository.findById(command.matchingPairId());

        if (optionalMatchingPair.isEmpty()) {
            throw new IllegalArgumentException("MatchingPair with ID " + command.matchingPairId() + " does not exist.");
        }

        MatchingPair matchingPair = optionalMatchingPair.get();
        var matchingItem = new MatchingItem(matchingPair, command);

        try {
            matchingItemRepository.save(matchingItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving matching item: " + e.getMessage());
        }

        return Optional.of(matchingItem);
    }

    @Override
    public void handle(DeleteMatchingItemCommand command) {
        if (!matchingItemRepository.existsById(command.matchingItemId())) {
            throw new IllegalArgumentException("MatchingItem with ID " + command.matchingItemId() + " does not exist.");
        }
        try {
            matchingItemRepository.deleteById(command.matchingItemId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting matching item with ID " + command.matchingItemId());
        }
    }
}
