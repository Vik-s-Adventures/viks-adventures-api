package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateObstacleOptionCommand;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "obstacle_options")
public class ObstacleOption extends AuditableAbstractAggregateRoot<ObstacleOption> {
    @ManyToOne
    @JoinColumn(name = "obstacle_id", nullable = false)
    private Obstacle obstacle;
    String optionText;
    Boolean isCorrect;

    public ObstacleOption(Obstacle obstacle, CreateObstacleOptionCommand command) {
        this(
                obstacle,
                command.optionText(),
                command.isCorrect()
        );
    }
}
