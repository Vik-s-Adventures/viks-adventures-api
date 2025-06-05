package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteObstacleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstaclesByFinalBattleIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.ObstacleCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.ObstacleQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.*;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/obstacles", produces = "application/json")
@Tag(name = "Obstacles", description = "Obstacle Management Endpoints")
public class ObstacleController {
    private final ObstacleCommandService obstacleCommandService;
    private final ObstacleQueryService obstacleQueryService;

    @PostMapping
    public ResponseEntity<ObstacleResource> createObstacle(@RequestBody CreateObstacleResource resource) {
        var command = CreateObstacleCommandFromResourceAssembler.toCommandFromResource(resource);
        var obstacle = obstacleCommandService.handle(command);
        if (obstacle.isEmpty()) return ResponseEntity.badRequest().build();
        var response = ObstacleResourceFromEntityAssembler.toResourceFromEntity(obstacle.get());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{obstacleId}")
    public ResponseEntity<ObstacleResource> getObstacleById(@PathVariable Long obstacleId) {
        var query = new GetObstacleByIdQuery(obstacleId);
        var obstacle = obstacleQueryService.handle(query);
        if (obstacle.isEmpty()) return ResponseEntity.notFound().build();
        var resource = ObstacleResourceFromEntityAssembler.toResourceFromEntity(obstacle.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/final-battle/{finalBattleId}")
    public ResponseEntity<List<ObstacleResource>> getObstaclesByFinalBattleId(@PathVariable Long finalBattleId) {
        var query = new GetObstaclesByFinalBattleIdQuery(finalBattleId);
        var obstacles = obstacleQueryService.handle(query);
        var resources = obstacles.stream()
                .map(ObstacleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{obstacleId}")
    public ResponseEntity<Void> deleteObstacle(@PathVariable Long obstacleId) {
        var deleteObstacleCommand = new DeleteObstacleCommand(obstacleId);
        obstacleCommandService.handle(deleteObstacleCommand);
        return ResponseEntity.noContent().build();
    }
}
