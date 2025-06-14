package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.SavePlayerLinkingResponseCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.SavePlayerLinkingResponseResource;

import java.util.List;

public class SavePlayerLinkingPairCommandFromResourceAssembler {

    public static SavePlayerLinkingResponseCommand toCommandFromResource(SavePlayerLinkingResponseResource resource) {
        List<SavePlayerLinkingResponseCommand.LinkingPairResponse> pairs = resource.pairs().stream()
                .map(r -> new SavePlayerLinkingResponseCommand.LinkingPairResponse(
                        r.linkingPairImageId(),
                        r.linkingPairAnswerId()
                ))
                .toList();

        return new SavePlayerLinkingResponseCommand(
                resource.playerId(),
                pairs
        );
    }
}