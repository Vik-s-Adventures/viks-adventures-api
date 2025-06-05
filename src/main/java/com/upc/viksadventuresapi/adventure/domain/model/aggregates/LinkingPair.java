package com.upc.viksadventuresapi.adventure.domain.model.aggregates;

import com.upc.viksadventuresapi.adventure.domain.model.commands.CreateLinkingPairCommand;
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
@Table(name = "linking_pairs")
public class LinkingPair extends AuditableAbstractAggregateRoot<LinkingPair> {
    @ManyToOne
    @JoinColumn(name = "linking_id", nullable = false)
    private Linking linking;

    @Embedded
    private ImageUrl imageUrl;

    @Embedded
    private Answer answer;

    public LinkingPair(Linking linking, CreateLinkingPairCommand command){
        this(
                linking,
                new ImageUrl(command.imageUrl()),
                new Answer(command.answer())
        );
    }
}
