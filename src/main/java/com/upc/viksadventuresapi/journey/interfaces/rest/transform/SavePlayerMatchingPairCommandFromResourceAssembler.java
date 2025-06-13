package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.PlayerMatchingPairRequest;
import com.upc.viksadventuresapi.journey.domain.model.commands.SavePlayerMatchingResponseCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.SavePlayerMatchingPairResource;

import java.util.List;

public class SavePlayerMatchingPairCommandFromResourceAssembler {
    public static SavePlayerMatchingResponseCommand toCommandFromResource(SavePlayerMatchingPairResource resource) {
        List<PlayerMatchingPairRequest> pairs = resource.pairs().stream()
                .map(r -> {
                    return new PlayerMatchingPairRequest(r.matchingItemAId(), r.matchingItemBId());
                })
                .toList();

        return new SavePlayerMatchingResponseCommand(
                resource.playerId(),
                resource.matchingId(),
                pairs
        );
    }
}