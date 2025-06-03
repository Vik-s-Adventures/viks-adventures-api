package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Riddle;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateRiddleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteRiddleCommand;
import com.upc.viksadventuresapi.adventure.domain.services.TrialRiddleCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.TrialRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.RiddleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiddleCommandServiceImpl implements TrialRiddleCommandService {
    private final TrialRepository trialRepository;
    private final RiddleRepository riddleRepository;

    @Override
    public Optional<Riddle> handle(CreateRiddleCommand command) {
        Optional<Trial> optionalTrial = trialRepository.findById(command.trialId());

        if (optionalTrial.isEmpty()) {
            throw new IllegalArgumentException("Trial with ID " + command.trialId() + " does not exist.");
        }

        Trial trial = optionalTrial.get();
        var trialRiddle = new Riddle(trial, command);

        try {
            riddleRepository.save(trialRiddle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving trial riddle: " + e.getMessage());
        }

        return Optional.of(trialRiddle);
    }

    @Override
    public void handle(DeleteRiddleCommand command) {
        if (!riddleRepository.existsById(command.riddleId())) {
            throw new IllegalArgumentException("Trial riddle with ID " + command.riddleId() + " does not exist.");
        }
        try {
            riddleRepository.deleteById(command.riddleId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting trial riddle with ID " + command.riddleId());
        }
    }
}
