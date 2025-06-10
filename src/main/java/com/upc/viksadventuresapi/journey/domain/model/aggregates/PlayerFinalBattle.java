package com.upc.viksadventuresapi.journey.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.aggregates.ObstacleOption;
import com.upc.viksadventuresapi.journey.domain.model.commands.CreatePlayerFinalBattleCommand;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "players_final_battles")
public class PlayerFinalBattle extends AuditableAbstractAggregateRoot<PlayerFinalBattle> {
    @ManyToOne
    @JoinColumn(name = "player_progress_id", nullable = false)
    private PlayerProgress playerProgress;

    @ManyToOne
    @JoinColumn(name = "obstacle_option_id", nullable = false)
    private ObstacleOption obstacleOption;
}
