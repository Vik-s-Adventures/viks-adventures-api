package com.upc.viksadventuresapi.journey.domain.model.aggregates;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "players_progress")
public class Player extends AuditableAbstractAggregateRoot<Player> {
    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false, unique = true)
    private Profile profile;

    private Integer totalScore;
}
