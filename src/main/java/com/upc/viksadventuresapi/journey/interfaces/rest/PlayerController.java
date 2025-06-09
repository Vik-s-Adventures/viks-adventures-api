package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.CreatePlayerCommandFromResourceAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.PlayerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/players", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Players", description = "Player Management Endpoints")
public class PlayerController {

    private final PlayerCommandService playerCommandService;
    private final PlayerQueryService playerQueryService;

    @PostMapping
    public ResponseEntity<PlayerResource> createPlayer(@RequestBody CreatePlayerResource resource) {
        var command = CreatePlayerCommandFromResourceAssembler.toCommandFromResource(resource);
        var player = playerCommandService.handle(command);
        if (player.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceResponse = PlayerResourceFromEntityAssembler.toResourceFromEntity(player.get());
        return ResponseEntity.status(201).body(resourceResponse);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResource> getPlayerById(@PathVariable Long playerId) {
        var query = new GetPlayerByIdQuery(playerId);
        var player = playerQueryService.handle(query);
        if (player.isEmpty()) return ResponseEntity.notFound().build();
        var resource = PlayerResourceFromEntityAssembler.toResourceFromEntity(player.get());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long playerId) {
        var command = new DeletePlayerCommand(playerId);
        playerCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}