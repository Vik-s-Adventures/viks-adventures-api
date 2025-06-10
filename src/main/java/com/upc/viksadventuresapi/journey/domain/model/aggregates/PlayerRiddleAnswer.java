package com.upc.viksadventuresapi.journey.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.RiddleDetail;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerRiddleAnswerCommand;
import com.upc.viksadventuresapi.journey.domain.model.valueobjects.EnteredAnswer;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "player_riddle_answers")
public class PlayerRiddleAnswer extends AuditableAbstractAggregateRoot<PlayerRiddleAnswer> {
    @ManyToOne
    @JoinColumn(name = "player_progress_id", nullable = false)
    private PlayerProgress playerProgress;

    @ManyToOne
    @JoinColumn(name = "riddle_detail_id", nullable = false)
    private RiddleDetail riddleDetail;

    @Embedded
    private EnteredAnswer enteredAnswer;

    public PlayerRiddleAnswer(PlayerProgress playerProgress, RiddleDetail riddleDetail, CreatePlayerRiddleAnswerCommand command) {
        this(
            playerProgress,
            riddleDetail,
            new EnteredAnswer(command.enteredAnswer())
        );
    }
}
