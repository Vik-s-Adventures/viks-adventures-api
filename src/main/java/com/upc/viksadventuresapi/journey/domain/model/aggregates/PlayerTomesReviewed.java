package com.upc.viksadventuresapi.journey.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Concept;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "player_tomes_reviewed")
public class PlayerTomesReviewed extends AuditableAbstractAggregateRoot<PlayerTomesReviewed> {
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "concept_id", nullable = false)
    private Concept concept;
}