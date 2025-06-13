package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerTomesReviewedCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerTomesReviewedByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerTomesReviewedCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerTomesReviewedQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerTomesReviewedResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerTomesReviewedResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.UpdatePlayerTomesReviewedResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.CreatePlayerTomesReviewedCommandFromResourceAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.PlayerTomesReviewedResourceFromEntityAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.UpdatePlayerTomesReviewedCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/player-tomes-reviewed", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Player Tomes Reviewed", description = "Tome Review Tracking Endpoints")
public class PlayerTomesReviewedController {

    private final PlayerTomesReviewedCommandService commandService;
    private final PlayerTomesReviewedQueryService queryService;

    @PostMapping
    public ResponseEntity<PlayerTomesReviewedResource> createPlayerTomesReviewed(@RequestBody CreatePlayerTomesReviewedResource resource) {
        var command = CreatePlayerTomesReviewedCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlayerTomesReviewedResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping
    public ResponseEntity<PlayerTomesReviewedResource> updatePlayerTomesReviewed(@RequestBody UpdatePlayerTomesReviewedResource resource) {
        var command = UpdatePlayerTomesReviewedCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlayerTomesReviewedResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerTomesReviewedResource> getByPlayerTomesReviewedId(@PathVariable Long id) {
        var query = new GetPlayerTomesReviewedByIdQuery(id);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var response = PlayerTomesReviewedResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerTomesReviewed(@PathVariable Long id) {
        var command = new DeletePlayerTomesReviewedCommand(id);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
