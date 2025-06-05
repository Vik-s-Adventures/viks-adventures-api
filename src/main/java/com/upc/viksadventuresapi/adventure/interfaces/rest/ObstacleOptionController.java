package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteObstacleOptionCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleOptionByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetObstacleOptionsByObstacleIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.ObstacleOptionCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.ObstacleOptionQueryService;
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
@RequestMapping(value = "/api/v1/obstacle-options", produces = "application/json")
@Tag(name = "Obstacle Options", description = "Obstacle Option Management Endpoints")
public class ObstacleOptionController {

    private final ObstacleOptionCommandService obstacleOptionCommandService;
    private final ObstacleOptionQueryService obstacleOptionQueryService;

    @PostMapping
    public ResponseEntity<ObstacleOptionResource> createObstacleOption(@RequestBody CreateObstacleOptionResource resource) {
        var command = CreateObstacleOptionCommandFromResourceAssembler.toCommandFromResource(resource);
        var option = obstacleOptionCommandService.handle(command);
        if (option.isEmpty()) return ResponseEntity.badRequest().build();
        var response = ObstacleOptionResourceFromEntityAssembler.toResourceFromEntity(option.get());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<ObstacleOptionResource> getObstacleOptionById(@PathVariable Long optionId) {
        var query = new GetObstacleOptionByIdQuery(optionId);
        var option = obstacleOptionQueryService.handle(query);
        if (option.isEmpty()) return ResponseEntity.notFound().build();
        var resource = ObstacleOptionResourceFromEntityAssembler.toResourceFromEntity(option.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/obstacle/{obstacleId}")
    public ResponseEntity<List<ObstacleOptionResource>> getOptionsByObstacleId(@PathVariable Long obstacleId) {
        var query = new GetObstacleOptionsByObstacleIdQuery(obstacleId);
        var options = obstacleOptionQueryService.handle(query);
        var resources = options.stream()
                .map(ObstacleOptionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{optionId}")
    public ResponseEntity<Void> deleteObstacleOption(@PathVariable Long optionId) {
        var command = new DeleteObstacleOptionCommand(optionId);
        obstacleOptionCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}