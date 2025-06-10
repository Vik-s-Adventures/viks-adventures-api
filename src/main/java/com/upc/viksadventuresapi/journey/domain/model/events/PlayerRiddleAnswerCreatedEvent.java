package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerRiddleAnswer;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlayerRiddleAnswerCreatedEvent extends ApplicationEvent {
    private final PlayerRiddleAnswer playerRiddleAnswer;

    public PlayerRiddleAnswerCreatedEvent(Object source, PlayerRiddleAnswer playerRiddleAnswer) {
        super(source);
        this.playerRiddleAnswer = playerRiddleAnswer;
    }
}
