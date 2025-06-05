package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Obstacle;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateObstacleOptionCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteObstacleOptionCommand;
import com.upc.viksadventuresapi.adventure.domain.services.ObstacleOptionCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ObstacleOptionRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ObstacleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObstacleOptionCommandServiceImpl implements ObstacleOptionCommandService {
    private final ObstacleRepository obstacleRepository;
    private final ObstacleOptionRepository obstacleOptionRepository;

    @Override
    public Optional<ObstacleOption> handle(CreateObstacleOptionCommand command) {
        Optional<Obstacle> optionalObstacle = obstacleRepository.findById(command.obstacleId());

        if (optionalObstacle.isEmpty()) {
            throw new IllegalArgumentException("Obstacle with ID " + command.obstacleId() + " does not exist.");
        }

        Obstacle obstacle = optionalObstacle.get();
        var obstacleOption = new ObstacleOption(obstacle, command);

        try {
            obstacleOptionRepository.save(obstacleOption);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving obstacle option: " + e.getMessage());
        }

        return Optional.of(obstacleOption);
    }

    @Override
    public void handle(DeleteObstacleOptionCommand command) {
        if (!obstacleOptionRepository.existsById(command.obstacleOptionId())) {
            throw new IllegalArgumentException("ObstacleOption with ID " + command.obstacleOptionId() + " does not exist.");
        }
        try {
            obstacleOptionRepository.deleteById(command.obstacleOptionId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting obstacle option with ID " + command.obstacleOptionId());
        }
    }
}
