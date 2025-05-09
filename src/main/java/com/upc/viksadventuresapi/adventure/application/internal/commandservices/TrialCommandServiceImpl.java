package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Trial;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTrialCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteTrialCommand;
import com.upc.viksadventuresapi.adventure.domain.services.TrialCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LevelRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.TrialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrialCommandServiceImpl implements TrialCommandService {
    private final TrialRepository trialRepository;
    private final LevelRepository levelRepository;

    @Override
    public Optional<Trial> handle(CreateTrialCommand command) {
        Optional<Level> optionalLevel = levelRepository.findById(command.levelId());

        if (optionalLevel.isEmpty()){
            throw new IllegalArgumentException("Quiz with ID " + command.levelId() + " does not exist.");
        }

        Level level = optionalLevel.get();
        var trial = new Trial(level, command);

        try {
            trialRepository.save(trial);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving trial: " + e.getMessage());
        }

        return Optional.of(trial);
    }

    @Override
    public void handle(DeleteTrialCommand command) {
        if (!trialRepository.existsById(command.trialId())) {
            throw new IllegalArgumentException("Trial with ID " + command.trialId() + " does not exist.");
        }
        try {
            trialRepository.deleteById(command.trialId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting trial with ID " + command.trialId());
        }
    }
}
