package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateObstacleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Description;
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
@Table(name = "obstacles")
public class Obstacle extends AuditableAbstractAggregateRoot<Obstacle> {
    @ManyToOne
    @JoinColumn(name = "final_battle_id", nullable = false)
    private FinalBattle finalBattle;

    @Embedded
    private ImageUrl imageUrl;

    @Embedded
    private Description description;

    public Obstacle(FinalBattle finalBattle, CreateObstacleCommand command){
        this(
                finalBattle,
                new ImageUrl(command.imageUrl()),
                new Description(command.description())
        );
    }
}
