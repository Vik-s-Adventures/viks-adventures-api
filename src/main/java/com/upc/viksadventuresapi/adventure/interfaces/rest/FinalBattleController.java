package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteFinalBattleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetAllFinalBattlesQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetFinalBattleByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetFinalBattlesByLevelIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.FinalBattleCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.FinalBattleQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateFinalBattleResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.FinalBattleResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateFinalBattleCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.FinalBattleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/final-battles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "FinalBattles", description = "FinalBattles Management Endpoints")
public class FinalBattleController {
    private final FinalBattleCommandService finalBattleCommandService;
    private final FinalBattleQueryService finalBattleQueryService;

    @PostMapping
    public ResponseEntity<FinalBattleResource> createFinalBattle(@RequestBody CreateFinalBattleResource resource) {
        var createFinalBattleCommand = CreateFinalBattleCommandFromResourceAssembler.toCommandFromResource(resource);
        var finalBattle = finalBattleCommandService.handle(createFinalBattleCommand);
        if (finalBattle.isEmpty()) return ResponseEntity.badRequest().build();
        var finalBattleResource = FinalBattleResourceFromEntityAssembler.toResourceFromEntity(finalBattle.get());
        return ResponseEntity.status(201).body(finalBattleResource);
    }

    @GetMapping("/{finalBattleId}")
    public ResponseEntity<FinalBattleResource> getFinalBattleById(@PathVariable Long finalBattleId) {
        var getFinalBattleByIdQuery = new GetFinalBattleByIdQuery(finalBattleId);
        var finalBattle = finalBattleQueryService.handle(getFinalBattleByIdQuery);
        if (finalBattle.isEmpty()) return ResponseEntity.notFound().build();
        var finalBattleResource = FinalBattleResourceFromEntityAssembler.toResourceFromEntity(finalBattle.get());
        return ResponseEntity.ok(finalBattleResource);
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<FinalBattleResource>> getFinalBattlesByLevelId(@PathVariable Long levelId) {
        var getFinalBattlesByLevelIdQuery = new GetFinalBattlesByLevelIdQuery(levelId);
        var finalBattles = finalBattleQueryService.handle(getFinalBattlesByLevelIdQuery);
        var finalBattleResources = finalBattles.stream()
                .map(FinalBattleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(finalBattleResources);
    }

    @GetMapping
    public ResponseEntity<List<FinalBattleResource>> getAllFinalBattles() {
        var getAllFinalBattlesQuery = new GetAllFinalBattlesQuery();
        var finalBattles = finalBattleQueryService.handle(getAllFinalBattlesQuery);
        var finalBattleResources = finalBattles.stream()
                .map(FinalBattleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(finalBattleResources);
    }

    @DeleteMapping("/{finalBattleId}")
    public ResponseEntity<Void> deleteFinalBattle(@PathVariable Long finalBattleId) {
        var deleteFinalBattleCommand = new DeleteFinalBattleCommand(finalBattleId);
        finalBattleCommandService.handle(deleteFinalBattleCommand);
        return ResponseEntity.noContent().build();
    }
}
