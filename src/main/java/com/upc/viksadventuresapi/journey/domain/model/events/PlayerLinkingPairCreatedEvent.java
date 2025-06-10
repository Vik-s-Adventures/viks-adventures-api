package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerLinkingPair;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlayerLinkingPairCreatedEvent extends ApplicationEvent {
    private final PlayerLinkingPair playerLinkingPair;

    public PlayerLinkingPairCreatedEvent(Object source, PlayerLinkingPair playerLinkingPair) {
        super(source);
        this.playerLinkingPair = playerLinkingPair;
    }
}
