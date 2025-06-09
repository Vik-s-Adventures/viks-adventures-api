package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerMatchingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerMatchingPairByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerMatchingPairCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerMatchingPairQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerMatchingPairResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerMatchingPairResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.CreatePlayerMatchingPairCommandFromResourceAssembler;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.PlayerMatchingPairResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/player-matching-pairs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Player Matching Pairs", description = "Matching Pair Tracking Endpoints")
public class PlayerMatchingPairController {

    private final PlayerMatchingPairCommandService commandService;
    private final PlayerMatchingPairQueryService queryService;

    @PostMapping
    public ResponseEntity<PlayerMatchingPairResource> createPlayerMatchingPair(@RequestBody CreatePlayerMatchingPairResource resource) {
        var command = CreatePlayerMatchingPairCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlayerMatchingPairResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerMatchingPairResource> getByPlayerMatchingPaiId(@PathVariable Long id) {
        var query = new GetPlayerMatchingPairByIdQuery(id);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var response = PlayerMatchingPairResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerMatchingPai(@PathVariable Long id) {
        var command = new DeletePlayerMatchingPairCommand(id);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
