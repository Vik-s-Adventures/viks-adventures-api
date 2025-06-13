package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayersQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.PlayerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/players", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Players", description = "Player Management Endpoints")
public class PlayerController {

    private final PlayerCommandService playerCommandService;
    private final PlayerQueryService playerQueryService;

    @GetMapping
    public ResponseEntity<List<PlayerResource>> getAllPlayers() {
        var query = new GetAllPlayersQuery();
        var players = playerQueryService.handle(query);

        var resources = players.stream()
                .map(PlayerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
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