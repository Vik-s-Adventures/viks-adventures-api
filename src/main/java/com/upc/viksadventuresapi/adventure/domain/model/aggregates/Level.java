package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLevelCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Name;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Performance;
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
@Table(name = "levels")
public class Level extends AuditableAbstractAggregateRoot<Level> {
    @ManyToOne
    @JoinColumn(name = "world_id", nullable = false)
    private World world;

    @Embedded
    private Name name;

    @Embedded
    private Performance performance;

    public Level(World world, CreateLevelCommand command) {
        this(world, new Name(command.name()), new Performance(command.performance()));
    }
}
