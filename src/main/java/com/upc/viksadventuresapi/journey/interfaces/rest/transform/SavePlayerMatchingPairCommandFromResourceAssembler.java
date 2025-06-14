package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.SavePlayerMatchingResponseCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.SavePlayerMatchingResponseResource;

import java.util.List;

public class SavePlayerMatchingPairCommandFromResourceAssembler {

    public static SavePlayerMatchingResponseCommand toCommandFromResource(SavePlayerMatchingResponseResource resource) {
        List<SavePlayerMatchingResponseCommand.MatchingPairResponse> pairs = resource.pairs().stream()
                .map(r -> new SavePlayerMatchingResponseCommand.MatchingPairResponse(
                        r.matchingItemAId(),
                        r.matchingItemBId()
                ))
                .toList();

        return new SavePlayerMatchingResponseCommand(
                resource.playerId(),
                pairs
        );
    }
}