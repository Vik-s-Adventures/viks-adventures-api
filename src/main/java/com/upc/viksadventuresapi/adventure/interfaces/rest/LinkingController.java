package com.upc.viksadventuresapi.adventure.interfaces.rest;

import com.upc.viksadventuresapi.adventure.domain.model.commands.DeleteLinkingCommand;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinkingByIdQuery;
import com.upc.viksadventuresapi.adventure.domain.model.queries.GetLinksByTrialIdQuery;
import com.upc.viksadventuresapi.adventure.domain.services.LinkingCommandService;
import com.upc.viksadventuresapi.adventure.domain.services.LinkingQueryService;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.CreateLinkingResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.resources.LinkingResource;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.CreateLinkingCommandFromResourceAssembler;
import com.upc.viksadventuresapi.adventure.interfaces.rest.transform.LinkingResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/links", produces = "application/json")
@Tag(name = "Links", description = "Links Management Endpoints")
public class LinkingController {
    private final LinkingCommandService linkingCommandService;
    private final LinkingQueryService linkingQueryService;

    @PostMapping
    public ResponseEntity<LinkingResource> createLinking(@RequestBody CreateLinkingResource resource) {
        var command = CreateLinkingCommandFromResourceAssembler.toCommandFromResource(resource);
        var linking = linkingCommandService.handle(command);
        if (linking.isEmpty()) return ResponseEntity.badRequest().build();
        var resourceResponse = LinkingResourceFromEntityAssembler.toResourceFromEntity(linking.get());
        return ResponseEntity.status(201).body(resourceResponse);
    }

    @GetMapping("/{linkingId}")
    public ResponseEntity<LinkingResource> getLinkingById(@PathVariable Long linkingId) {
        var query = new GetLinkingByIdQuery(linkingId);
        var linking = linkingQueryService.handle(query);
        if (linking.isEmpty()) return ResponseEntity.notFound().build();
        var resource = LinkingResourceFromEntityAssembler.toResourceFromEntity(linking.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/trial/{trialId}")
    public ResponseEntity<List<LinkingResource>> getLinksByTrialId(@PathVariable Long trialId) {
        var query = new GetLinksByTrialIdQuery(trialId);
        var links = linkingQueryService.handle(query);
        var resources = links.stream()
                .map(LinkingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping
    public ResponseEntity<List<LinkingResource>> getAllLinks() {
        var query = new GetLinksByTrialIdQuery(null); // Assuming this returns all links
        var links = linkingQueryService.handle(query);
        var resources = links.stream()
                .map(LinkingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{linkingId}")
    public ResponseEntity<Void> deleteLinking(@PathVariable Long linkingId) {
        var deleteLinkingCommand = new DeleteLinkingCommand(linkingId);
        linkingCommandService.handle(deleteLinkingCommand);
        return ResponseEntity.noContent().build();
    }
}
