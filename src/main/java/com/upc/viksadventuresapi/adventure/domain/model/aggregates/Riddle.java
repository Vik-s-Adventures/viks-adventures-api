package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateRiddleCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Question;
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
@Table(name = "riddles")
public class Riddle extends AuditableAbstractAggregateRoot<Riddle> {
    @ManyToOne
    @JoinColumn(name = "trial_id", nullable = false)
    private Trial trial;

    @Embedded
    private Question question;

    public Riddle(Trial trial, CreateRiddleCommand command) {
        this(
                trial,
                new Question(command.question())
        );
    }
}
