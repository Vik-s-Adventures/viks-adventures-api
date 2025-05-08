package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

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
@Table(name = "paths")
public class Path extends AuditableAbstractAggregateRoot<Path> {
    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @Embedded
    private Description description;

    @Embedded
    private ImageUrl imageUrl;
}
