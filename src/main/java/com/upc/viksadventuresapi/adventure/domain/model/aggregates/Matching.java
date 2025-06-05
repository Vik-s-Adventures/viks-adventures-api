package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateMatchingCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Description;
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
@Table(name = "matchings")
public class Matching extends AuditableAbstractAggregateRoot<Matching> {
    @ManyToOne
    @JoinColumn(name = "trial_id", nullable = false)
    private Trial trial;

    @Embedded
    private Description description;

    @OneToMany(mappedBy = "matching", cascade = CascadeType.ALL)
    private List<MatchingPair> matchingPairs = new ArrayList<>();

    public Matching(Trial trial, Description description) {
        this.trial = trial;
        this.description = description;
        this.matchingPairs = new ArrayList<>();
    }

    public Matching(Trial trial, CreateMatchingCommand command) {
        this(
                trial,
                new Description(command.description())
        );
    }
}
