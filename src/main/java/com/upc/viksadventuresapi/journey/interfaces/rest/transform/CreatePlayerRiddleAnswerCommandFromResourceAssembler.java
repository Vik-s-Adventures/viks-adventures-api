package com.upc.viksadventuresapi.journey.interfaces.rest.transform;

import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.interfaces.rest.resources.CreatePlayerRiddleAnswerResource;

public class CreatePlayerRiddleAnswerCommandFromResourceAssembler {
    public static CreatePlayerRiddleAnswerCommand toCommandFromResource(CreatePlayerRiddleAnswerResource resource) {
        return new CreatePlayerRiddleAnswerCommand(
                resource.playerProgressId(),
                resource.riddleDetailId(),
                resource.enteredAnswer()
        );
    }
}
