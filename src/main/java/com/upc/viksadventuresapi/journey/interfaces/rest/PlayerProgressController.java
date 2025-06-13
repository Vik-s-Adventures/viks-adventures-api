package com.upc.viksadventuresapi.journey.interfaces.rest;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerProgressCommand;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayerProgressesByLevelIdQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetAllPlayerProgressesByPlayerIdAndLevelIdQuery;
import com.upc.viksadventuresapi.journey.domain.model.queries.GetPlayerProgressByIdQuery;
import com.upc.viksadventuresapi.journey.domain.services.PlayerProgressCommandService;
import com.upc.viksadventuresapi.journey.domain.services.PlayerProgressQueryService;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.PlayerProgressResource;
import com.upc.viksadventuresapi.journey.interfaces.rest.transform.PlayerProgressResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/player-progress", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Player Progress", description = "Player Progress Management Endpoints")
public class PlayerProgressController {

    private final PlayerProgressCommandService commandService;
    private final PlayerProgressQueryService queryService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerProgressResource> getPlayerProgressById(@PathVariable Long id) {
        var query = new GetPlayerProgressByIdQuery(id);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var response = PlayerProgressResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<PlayerProgressResource>> getAllByLevelId(@PathVariable Long levelId) {
        var query = new GetAllPlayerProgressesByLevelIdQuery(levelId);
        var progresses = queryService.handle(query);
        var resources = progresses.stream()
                .map(PlayerProgressResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/player/{playerId}/level/{levelId}")
    public ResponseEntity<List<PlayerProgressResource>> getAllByPlayerIdAndLevelId(
            @PathVariable Long playerId,
            @PathVariable Long levelId) {
        var query = new GetAllPlayerProgressesByPlayerIdAndLevelIdQuery(playerId, levelId);
        var progresses = queryService.handle(query);
        var resources = progresses.stream()
                .map(PlayerProgressResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerProgress(@PathVariable Long id) {
        var command = new DeletePlayerProgressCommand(id);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
