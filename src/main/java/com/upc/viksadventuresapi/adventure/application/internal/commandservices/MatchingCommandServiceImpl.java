package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteMatchingCommand;
import com.upc.viksadventuresapi.adventure.domain.services.MatchingCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.MatchingRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.TrialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingCommandServiceImpl implements MatchingCommandService {
    private final TrialRepository trialRepository;
    private final MatchingRepository matchingRepository;

    @Override
    public Optional<Matching> handle(CreateMatchingCommand command) {
        Optional<Trial> optionalTrial = trialRepository.findById(command.trialId());

        if (optionalTrial.isEmpty()) {
            throw new IllegalArgumentException("Trial with ID " + command.trialId() + " does not exist.");
        }

        Trial trial = optionalTrial.get();
        var matching = new Matching(trial, command);

        try {
            matchingRepository.save(matching);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving trial matching: " + e.getMessage());
        }

        return Optional.of(matching);
    }

    @Override
    public void handle(DeleteMatchingCommand command) {
        if (!matchingRepository.existsById(command.matchingId())) {
            throw new IllegalArgumentException("Trial matching with ID " + command.matchingId() + " does not exist.");
        }
        try {
            matchingRepository.deleteById(command.matchingId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting trial matching with ID " + command.matchingId());
        }
    }
}
