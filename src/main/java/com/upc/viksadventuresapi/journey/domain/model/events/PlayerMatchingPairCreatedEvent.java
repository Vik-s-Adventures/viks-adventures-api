package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerMatchingPair;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlayerMatchingPairCreatedEvent extends ApplicationEvent {
    private final PlayerMatchingPair playerMatchingPair;

    public PlayerMatchingPairCreatedEvent(Object source, PlayerMatchingPair playerMatchingPair) {
        super(source);
        this.playerMatchingPair = playerMatchingPair;
    }
}
