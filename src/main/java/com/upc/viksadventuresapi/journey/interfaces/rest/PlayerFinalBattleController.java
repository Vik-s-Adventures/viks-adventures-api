package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerFinalBattleCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerFinalBattleByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerFinalBattleCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerFinalBattleQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerFinalBattleResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerFinalBattleResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.UpdatePlayerFinalBattleResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.CreatePlayerFinalBattleCommandFromResourceAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.PlayerFinalBattleResourceFromEntityAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.UpdatePlayerFinalBattleCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/player-final-battles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Player Final Battles", description = "Final Battle Tracking Endpoints")
public class PlayerFinalBattleController {

    private final PlayerFinalBattleCommandService commandService;
    private final PlayerFinalBattleQueryService queryService;

    @PostMapping
    public ResponseEntity<PlayerFinalBattleResource> createPlayerFinalBattle(@RequestBody CreatePlayerFinalBattleResource resource) {
        var command = CreatePlayerFinalBattleCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlayerFinalBattleResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping
    public ResponseEntity<PlayerFinalBattleResource> updatePlayerFinalBattle(@RequestBody UpdatePlayerFinalBattleResource resource) {
        var command = UpdatePlayerFinalBattleCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlayerFinalBattleResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerFinalBattleResource> getByPlayerFinalBattleId(@PathVariable Long id) {
        var query = new GetPlayerFinalBattleByIdQuery(id);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var response = PlayerFinalBattleResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerFinalBattle(@PathVariable Long id) {
        var command = new DeletePlayerFinalBattleCommand(id);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
