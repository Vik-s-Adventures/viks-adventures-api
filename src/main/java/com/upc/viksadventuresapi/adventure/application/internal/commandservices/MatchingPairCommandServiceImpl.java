package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingPair;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingPairCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingPairCommand;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingPairCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingPairRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingPairCommandServiceImpl implements MatchingPairCommandService {
    private final MatchingRepository matchingRepository;
    private final MatchingPairRepository matchingPairRepository;

    @Override
    public Optional<MatchingPair> handle(CreateMatchingPairCommand command) {
        Optional<Matching> optionalMatching = matchingRepository.findById(command.matchingId());

        if (optionalMatching.isEmpty()) {
            throw new IllegalArgumentException("Matching with ID " + command.matchingId() + " does not exist.");
        }

        Matching matching = optionalMatching.get();
        var matchingPair = new MatchingPair(matching);

        try {
            matchingPairRepository.save(matchingPair);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving matching pair: " + e.getMessage());
        }

        return Optional.of(matchingPair);
    }

    @Override
    public void handle(DeleteMatchingPairCommand command) {
        if (!matchingPairRepository.existsById(command.matchingPairId())) {
            throw new IllegalArgumentException("Matching pair with ID " + command.matchingPairId() + " does not exist.");
        }
        try {
            matchingPairRepository.deleteById(command.matchingPairId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting matching pair with ID " + command.matchingPairId());
        }
    }
}
