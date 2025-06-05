package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "matching_pairs")
public class MatchingPair extends AuditableAbstractAggregateRoot<MatchingPair> {
    @ManyToOne
    @JoinColumn(name = "matching_id", nullable = false)
    private Matching matching;

    public MatchingPair(Matching matching) {
        this.matching = matching;
    }

    @OneToMany(mappedBy = "matchingPair", cascade = CascadeType.ALL)
    private List<MatchingItem> matchingItems = new ArrayList<>();
}