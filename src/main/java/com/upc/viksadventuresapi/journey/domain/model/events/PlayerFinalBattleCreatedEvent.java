package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerFinalBattle;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlayerFinalBattleCreatedEvent extends ApplicationEvent {
    private final PlayerFinalBattle playerFinalBattle;

    public PlayerFinalBattleCreatedEvent(Object source, PlayerFinalBattle playerFinalBattle) {
        super(source);
        this.playerFinalBattle = playerFinalBattle;
    }
}
