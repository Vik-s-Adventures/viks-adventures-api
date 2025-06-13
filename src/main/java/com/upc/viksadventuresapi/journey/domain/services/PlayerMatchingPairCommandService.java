package com.upc.viksadventuresapi.journey.domain.services;

import com.upc.viksadventuresapi.journey.domain.model.commands.DeletePlayerMatchingPairCommand;
import com.upc.viksadventuresapi.journey.domain.model.commands.SavePlayerMatchingResponseCommand;

public interface PlayerMatchingPairCommandService {
    void handle(SavePlayerMatchingResponseCommand command);
    void handle(DeletePlayerMatchingPairCommand command);
}
