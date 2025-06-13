package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Matching;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlayerMatchingPairCreatedEvent extends ApplicationEvent {

    private final Player player;
    private final Matching matching;

    public PlayerMatchingPairCreatedEvent(Object source, Player player, Matching matching) {
        super(source);
        this.player = player;
        this.matching = matching;
    }
}