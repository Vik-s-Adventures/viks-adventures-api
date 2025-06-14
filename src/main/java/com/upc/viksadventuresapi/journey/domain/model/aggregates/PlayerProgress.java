package com.upc.viksadventuresapi.journey.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.Level;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerProgressCommand;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "players_progress")
public class PlayerProgress extends AuditableAbstractAggregateRoot<PlayerProgress> {
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    private Boolean completed;
    private Integer score;
    private LocalDateTime lastAccessed;

    public PlayerProgress(Player player, Level level, CreatePlayerProgressCommand command) {
        this(
            player,
            level,
            command.completed(),
            command.score(),
            command.lastAccessed()
        );
    }
}