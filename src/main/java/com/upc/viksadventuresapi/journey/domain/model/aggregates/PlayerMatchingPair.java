package com.upc.viksadventuresapi.journey.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.MatchingItem;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "player_matching_pairs")
public class PlayerMatchingPair extends AuditableAbstractAggregateRoot<PlayerMatchingPair> {
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "matching_item_a_id", nullable = false)
    private MatchingItem matchingItemA;

    @ManyToOne
    @JoinColumn(name = "matching_item_b_id", nullable = false)
    private MatchingItem matchingItemB;
}