package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateConceptCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Description;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.ImageUrl;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Subtitle;
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
@Table(name = "concepts")
public class Concept extends AuditableAbstractAggregateRoot<Concept> {
    @ManyToOne
    @JoinColumn(name = "tome_id", nullable = false)
    private Tome tome;

    @Embedded
    private Subtitle subtitle;

    @Embedded
    private Description description;

    @Embedded
    private ImageUrl imageUrl;

    public Concept(Tome tome, CreateConceptCommand command) {
        this(
                tome,
                new Subtitle(command.subtitle()),
                new Description(command.description()),
                new ImageUrl(command.imageUrl())
        );
    }
}
