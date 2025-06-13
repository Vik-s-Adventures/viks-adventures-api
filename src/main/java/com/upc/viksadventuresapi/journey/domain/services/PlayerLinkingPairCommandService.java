package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerLinkingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.SavePlayerLinkingResponseCommand;

public interface PlayerLinkingPairCommandService {
    void handle(SavePlayerLinkingResponseCommand command);
    void handle(DeletePlayerLinkingPairCommand command);
}
