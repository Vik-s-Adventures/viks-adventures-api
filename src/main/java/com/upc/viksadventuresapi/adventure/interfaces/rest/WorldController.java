package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteWorldCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllWorldsQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetWorldByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.WorldCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.WorldQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateWorldResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.WorldResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateWorldCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.WorldResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/worlds", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Worlds", description = "Worlds Management Endpoints")
public class WorldController {

    private final WorldCommandService worldCommandService;
    private final WorldQueryService worldQueryService;

    @PostMapping
    public ResponseEntity<WorldResource> createWorld(@RequestBody CreateWorldResource resource) {
        var command = CreateWorldCommandFromResourceAssembler.toCommandFromResource(resource);
        var world = worldCommandService.handle(command);
        if (world.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceResponse = WorldResourceFromEntityAssembler.toResourceFromEntity(world.get());
        return ResponseEntity.status(201).body(resourceResponse);
    }

    @GetMapping("/{worldId}")
    public ResponseEntity<WorldResource> getWorldById(@PathVariable Long worldId) {
        var query = new GetWorldByIdQuery(worldId);
        var world = worldQueryService.handle(query);
        if (world.isEmpty()) return ResponseEntity.notFound().build();
        var resource = WorldResourceFromEntityAssembler.toResourceFromEntity(world.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<WorldResource>> getAllWorlds() {
        var query = new GetAllWorldsQuery();
        var worlds = worldQueryService.handle(query);
        var resources = worlds.stream()
                .map(WorldResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{worldId}")
    public ResponseEntity<Void> deleteWorld(@PathVariable Long worldId) {
        var command = new DeleteWorldCommand(worldId);
        worldCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}