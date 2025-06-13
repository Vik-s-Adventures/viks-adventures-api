package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.PlayerLinkingPairRequest;
import com.upc.viksadventuresapi.journey.domain.model.commands.SavePlayerLinkingResponseCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.SavePlayerLinkingResponseResource;

import java.util.List;

public class SavePlayerLinkingPairCommandFromResourceAssembler {
    public static SavePlayerLinkingResponseCommand toCommandFromResource(SavePlayerLinkingResponseResource resource) {
        List<PlayerLinkingPairRequest> pairs = resource.pairs().stream()
                .map(r -> new PlayerLinkingPairRequest(
                        r.linkingPairId(),
                        r.linkingPairImageId(),
                        r.linkingPairAnswerId()
                ))
                .toList();

        return new SavePlayerLinkingResponseCommand(
                resource.playerId(),
                resource.linkingId(),
                pairs
        );
    }
}