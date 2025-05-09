package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLevelCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllLevelsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLevelByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLevelsByWorldIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.LevelCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.LevelQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateLevelResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.LevelResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateLevelCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.LevelResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/levels", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Levels", description = "Levels Management Endpoints")
public class LevelController {

    private final LevelCommandService levelCommandService;
    private final LevelQueryService levelQueryService;

    @PostMapping
    public ResponseEntity<LevelResource> createLevel(@RequestBody CreateLevelResource resource) {
        var createLevelCommand = CreateLevelCommandFromResourceAssembler.toCommandFromResource(resource);
        var level = levelCommandService.handle(createLevelCommand);
        if (level.isEmpty()) return ResponseEntity.badRequest().build();
        var levelResource = LevelResourceFromEntityAssembler.toResourceFromEntity(level.get());
        return ResponseEntity.status(201).body(levelResource);
    }

    @GetMapping("/{levelId}")
    public ResponseEntity<LevelResource> getLevelById(@PathVariable Long levelId) {
        var getLevelByIdQuery = new GetLevelByIdQuery(levelId);
        var level = levelQueryService.handle(getLevelByIdQuery);
        if (level.isEmpty()) return ResponseEntity.notFound().build();
        var levelResource = LevelResourceFromEntityAssembler.toResourceFromEntity(level.get());
        return ResponseEntity.ok(levelResource);
    }

    @GetMapping("/world/{worldId}")
    public ResponseEntity<List<LevelResource>> getLevelsByWorldId(@PathVariable Long worldId) {
        var getLevelsByWorldIdQuery = new GetLevelsByWorldIdQuery(worldId);
        var levels = levelQueryService.handle(getLevelsByWorldIdQuery);
        var levelResources = levels.stream()
                .map(LevelResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(levelResources);
    }

    @GetMapping
    public ResponseEntity<List<LevelResource>> getAllLevels() {
        var getAllLevelsQuery = new GetAllLevelsQuery();
        var levels = levelQueryService.handle(getAllLevelsQuery);
        var levelResources = levels.stream()
                .map(LevelResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(levelResources);
    }

    @DeleteMapping("/{levelId}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long levelId) {
        var deleteLevelCommand = new DeleteLevelCommand(levelId);
        levelCommandService.handle(deleteLevelCommand);
        return ResponseEntity.noContent().build();
    }
}
