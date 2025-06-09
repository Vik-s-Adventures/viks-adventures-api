package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerProgressByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerProgressCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerProgressQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerProgressResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerProgressResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.CreatePlayerProgressCommandFromResourceAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.PlayerProgressResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/player-progress", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Player Progress", description = "Player Progress Management Endpoints")
public class PlayerProgressController {

    private final PlayerProgressCommandService commandService;
    private final PlayerProgressQueryService queryService;

    @PostMapping
    public ResponseEntity<PlayerProgressResource> createPlayerProgress(@RequestBody CreatePlayerProgressResource resource) {
        var command = CreatePlayerProgressCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlayerProgressResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerProgressResource> getPlayerProgressById(@PathVariable Long id) {
        var query = new GetPlayerProgressByIdQuery(id);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var response = PlayerProgressResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerProgress(@PathVariable Long id) {
        var command = new DeletePlayerProgressCommand(id);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
