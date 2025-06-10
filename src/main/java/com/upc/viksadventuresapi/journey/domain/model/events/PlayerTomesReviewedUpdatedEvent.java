package com.upc.viksadventuresapi.journey.domain.model.events;

import com.upc.viksadventuresapi.journey.domain.model.aggregates.PlayerTomesReviewed;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlayerTomesReviewedUpdatedEvent extends ApplicationEvent {
    private final PlayerTomesReviewed playerTomesReviewed;

    public PlayerTomesReviewedUpdatedEvent(Object source, PlayerTomesReviewed playerTomesReviewed) {
        super(source);
        this.playerTomesReviewed = playerTomesReviewed;
    }
}
