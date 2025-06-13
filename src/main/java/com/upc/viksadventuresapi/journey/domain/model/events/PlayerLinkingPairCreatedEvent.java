package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Linking;
import com.upc.viksadventuresapi.journey.domain.model.aggregates.Player;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlayerLinkingPairCreatedEvent extends ApplicationEvent {

    private final Player player;
    private final Linking linking;

    public PlayerLinkingPairCreatedEvent(Object source, Player player, Linking linking) {
        super(source);
        this.player = player;
        this.linking = linking;
    }
}
