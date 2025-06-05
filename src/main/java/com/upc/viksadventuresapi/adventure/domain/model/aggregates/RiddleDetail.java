package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateRiddleDetailCommand;
import com.upc.viksadventuresapi.adventure.domain.model.valueobjects.Answer;
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
@Table(name = "riddle_details")
public class RiddleDetail extends AuditableAbstractAggregateRoot<RiddleDetail> {
    @ManyToOne
    @JoinColumn(name = "riddle_id", nullable = false)
    private Riddle riddle;

    @Embedded
    private ImageUrl imageUrl;

    @Embedded
    private Answer answer;

    public RiddleDetail(Riddle riddle, CreateRiddleDetailCommand command) {
        this(
                riddle,
                new ImageUrl(command.imageUrl()),
                new Answer(command.answer())
        );
    }
}
