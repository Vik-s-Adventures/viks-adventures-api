package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.UpdatePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.UpdatePlayerRiddleAnswerResource;

public class UpdatePlayerRiddleAnswerCommandFromResourceAssembler {
    public static UpdatePlayerRiddleAnswerCommand toCommandFromResource(UpdatePlayerRiddleAnswerResource resource) {
        return new UpdatePlayerRiddleAnswerCommand(
                resource.playerId(),
                resource.riddleDetailId(),
                resource.enteredAnswer()
        );
    }
}
