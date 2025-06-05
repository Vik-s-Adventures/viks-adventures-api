package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLinkingCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Description;
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
@Table(name = "linkings")
public class Linking extends AuditableAbstractAggregateRoot<Linking> {
    @ManyToOne
    @JoinColumn(name = "trial_id", nullable = false)
    private Trial trial;

    @Embedded
    private Description description;

    public Linking(Trial trial, CreateLinkingCommand command) {
        this(
            trial,
            new Description(command.description()
        ));
    }
}
