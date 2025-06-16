package com.upc.viksadventuresapi.adventure.application.internal.commandservices;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.FinalBattle;
import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Obstacle;
import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateObstacleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteObstacleCommand;
import com.upc.viksadventuresapi.adventure.domain.services.ObstacleCommandService;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.FinalBattleRepository;
import com.upc.viksadventuresapi.adventure.infrastructure.persistence.jpa.repositories.ObstacleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObstacleCommandServiceImpl implements ObstacleCommandService {
    private final FinalBattleRepository finalBattleRepository;
    private final ObstacleRepository obstacleRepository;

    @Override
    public Optional<Obstacle> handle(CreateObstacleCommand command) {
            System.out.println("INPUT imageUrl: [" + command.imageUrl() + "]");
            System.out.println("INPUT description: [" + command.description() + "]");
            System.out.println("INPUT finalBattleId: [" + command.finalBattleId() + "]");


            Optional<FinalBattle> optionalFinalBattle = finalBattleRepository.findById(command.finalBattleId());

        if (optionalFinalBattle.isEmpty()) {
            throw new IllegalArgumentException("FinalBattle with ID " + command.finalBattleId() + " does not exist.");
        }

        FinalBattle finalBattle = optionalFinalBattle.get();
        var obstacle = new Obstacle(finalBattle, command);

        try {
            obstacleRepository.save(obstacle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving obstacle: " + e.getMessage());
        }

        return Optional.of(obstacle);
    }

    @Override
    public void handle(DeleteObstacleCommand command) {
        if (!obstacleRepository.existsById(command.obstacleId())) {
            throw new IllegalArgumentException("ObstacleOption with ID " + command.obstacleId() + " does not exist.");
        }
        try {
            obstacleRepository.deleteById(command.obstacleId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting obstacle option with ID " + command.obstacleId());
        }
    }
}
