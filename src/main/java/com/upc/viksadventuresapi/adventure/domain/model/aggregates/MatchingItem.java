package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingItemCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.ImageUrl;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "matching_items")
public class MatchingItem extends AuditableAbstractAggregateRoot<MatchingItem> {
    @ManyToOne
    @JoinColumn(name = "matching_id")
    private Matching matching;

    @ManyToOne
    @JoinColumn(name = "matching_pair_id")
    private MatchingPair matchingPair;

    @Embedded
    private ImageUrl imageUrl;

    boolean isDistractor;

    public MatchingItem(Matching matching, MatchingPair matchingPair, CreateMatchingItemCommand command) {
        this(
                matching,
                matchingPair,
                new ImageUrl(command.imageUrl()),
                command.isDistractor()
        );
    }
}
