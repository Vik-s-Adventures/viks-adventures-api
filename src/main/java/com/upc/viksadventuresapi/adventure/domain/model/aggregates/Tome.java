package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateTomeCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Advice;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Title;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Welcome;
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
@Table(name = "tomes")
public class Tome extends AuditableAbstractAggregateRoot<Tome> {
    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @Embedded
    private Title title;

    @Embedded
    private Welcome welcome;

    @Embedded
    private Advice advice;

    public Tome(Level level, CreateTomeCommand command) {
        this(level, new Title(command.title()), new Welcome(command.welcome()), new Advice(command.advice()));
    }
}
