package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLinkingPairCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingPairByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingPairsByLinkingIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.LinkingPairCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.LinkingPairQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateLinkingPairResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.LinkingPairResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateLinkingPairCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.LinkingPairResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/linking-pairs", produces = "application/json")
@Tag(name = "Linking Pairs", description = "Linking Pairs Management Endpoints")
public class LinkingPairController {
    private final LinkingPairCommandService linkingPairCommandService;
    private final LinkingPairQueryService linkingPairQueryService;

    @PostMapping
    public ResponseEntity<LinkingPairResource> createLinkingPair(@RequestBody CreateLinkingPairResource resource) {
        var command = CreateLinkingPairCommandFromResourceAssembler.toCommandFromResource(resource);
        var linkingPair = linkingPairCommandService.handle(command);
        if (linkingPair.isEmpty()) return ResponseEntity.badRequest().build();
        var response = LinkingPairResourceFromEntityAssembler.toResourceFromEntity(linkingPair.get());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{linkingPairId}")
    public ResponseEntity<LinkingPairResource> getLinkingPairById(@PathVariable Long linkingPairId) {
        var query = new GetLinkingPairByIdQuery(linkingPairId);
        var linkingPair = linkingPairQueryService.handle(query);
        if (linkingPair.isEmpty()) return ResponseEntity.notFound().build();
        var resource = LinkingPairResourceFromEntityAssembler.toResourceFromEntity(linkingPair.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/linking/{linkingId}")
    public ResponseEntity<List<LinkingPairResource>> getLinkingPairsByLinkingId(@PathVariable Long linkingId) {
        var query = new GetLinkingPairsByLinkingIdQuery(linkingId);
        var linkingPairs = linkingPairQueryService.handle(query);
        var resources = linkingPairs.stream()
                .map(LinkingPairResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{linkingPairId}")
    public ResponseEntity<Void> deleteLinkingPair(@PathVariable Long linkingPairId) {
        var command = new DeleteLinkingPairCommand(linkingPairId);
        linkingPairCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }

}
