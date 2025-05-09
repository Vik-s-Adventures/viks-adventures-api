package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Tome;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTomeCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteTomeCommand;
import com.upc.viksadventuresapi.adventure.domain.services.TomeCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LevelRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.TomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TomeCommandServiceImpl implements TomeCommandService {
    private final TomeRepository tomeRepository;
    private final LevelRepository levelRepository;

    @Override
    public Optional<Tome> handle(CreateTomeCommand command) {
        Optional<Level> optionalLevel = levelRepository.findById(command.levelId());

        if (optionalLevel.isEmpty()){
            throw new IllegalArgumentException("Quiz with ID " + command.levelId() + " does not exist.");
        }

        Level level = optionalLevel.get();
        var tome = new Tome(level, command);

        try {
            tomeRepository.save(tome);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving tome: " + e.getMessage());
        }

        return Optional.of(tome);
    }

    @Override
    public void handle(DeleteTomeCommand command) {
        if (!tomeRepository.existsById(command.tomeId())) {
            throw new IllegalArgumentException("Tome with ID " + command.tomeId() + " does not exist.");
        }
        try {
            tomeRepository.deleteById(command.tomeId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting tome with ID " + command.tomeId());
        }
    }
}
