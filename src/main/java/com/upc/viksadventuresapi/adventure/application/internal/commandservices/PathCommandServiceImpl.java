package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Path;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreatePathCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeletePathCommand;
import com.upc.viksadventuresapi.adventure.domain.services.PathCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.LevelRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.PathRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PathCommandServiceImpl implements PathCommandService {
    private final PathRepository pathRepository;
    private final LevelRepository levelRepository;

    @Override
    public Optional<Path> handle(CreatePathCommand command) {
        Optional<Level> optionalLevel = levelRepository.findById(command.levelId());

        if (optionalLevel.isEmpty()){
            throw new IllegalArgumentException("Quiz with ID " + command.levelId() + " does not exist.");
        }

        Level level = optionalLevel.get();
        var path = new Path(level, command);

        try {
            pathRepository.save(path);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving path: " + e.getMessage());
        }

        return Optional.of(path);
    }

    @Override
    public void handle(DeletePathCommand command) {
        if (!pathRepository.existsById(command.pathId())) {
            throw new IllegalArgumentException("Path with ID " + command.pathId() + " does not exist.");
        }
        try {
            pathRepository.deleteById(command.pathId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting path with ID " + command.pathId());
        }
    }
}
