package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteTomeCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllTomesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTomeByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetTomesByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.TomeCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.TomeQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateTomeResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.TomeResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateTomeCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.TomeResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/tomes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tomes", description = "Tomes Management Endpoints")
public class TomeController {

    private final TomeCommandService tomeCommandService;
    private final TomeQueryService tomeQueryService;

    @PostMapping
    public ResponseEntity<TomeResource> createTome(@RequestBody CreateTomeResource resource) {
        var createTomeCommand = CreateTomeCommandFromResourceAssembler.toCommandFromResource(resource);
        var tome = tomeCommandService.handle(createTomeCommand);
        if (tome.isEmpty()) return ResponseEntity.badRequest().build();
        var tomeResource = TomeResourceFromEntityAssembler.toResourceFromEntity(tome.get());
        return ResponseEntity.status(201).body(tomeResource);
    }

    @GetMapping("/{tomeId}")
    public ResponseEntity<TomeResource> getTomeById(@PathVariable Long tomeId) {
        var getTomeByIdQuery = new GetTomeByIdQuery(tomeId);
        var tome = tomeQueryService.handle(getTomeByIdQuery);
        if (tome.isEmpty()) return ResponseEntity.notFound().build();
        var tomeResource = TomeResourceFromEntityAssembler.toResourceFromEntity(tome.get());
        return ResponseEntity.ok(tomeResource);
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<TomeResource>> getTomesByLevelId(@PathVariable Long levelId) {
        var getTomesByPathIdQuery = new GetTomesByLevelIdQuery(levelId);
        var tomes = tomeQueryService.handle(getTomesByPathIdQuery);
        var tomeResources = tomes.stream()
                .map(TomeResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tomeResources);
    }

    @GetMapping
    public ResponseEntity<List<TomeResource>> getAllTomes() {
        var getAllTomesQuery = new GetAllTomesQuery();
        var tomes = tomeQueryService.handle(getAllTomesQuery);
        var tomeResources = tomes.stream()
                .map(TomeResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tomeResources);
    }

    @DeleteMapping("/{tomeId}")
    public ResponseEntity<Void> deleteTome(@PathVariable Long tomeId) {
        var deleteTomeCommand = new DeleteTomeCommand(tomeId);
        tomeCommandService.handle(deleteTomeCommand);
        return ResponseEntity.noContent().build();
    }
}