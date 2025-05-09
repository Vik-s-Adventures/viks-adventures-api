package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateWorldCommand;
import com.upc.viksadventuresapi.adventure.domain.model.enums.CompetenceType;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Name;
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
@Table(name = "worlds")
public class World extends AuditableAbstractAggregateRoot<World> {
    @Embedded
    Name name;

    @Enumerated(EnumType.STRING)
    CompetenceType competenceType;

    public World(CreateWorldCommand command){
        this(new Name(command.name()), CompetenceType.valueOf(command.competenceType()));
    }
}