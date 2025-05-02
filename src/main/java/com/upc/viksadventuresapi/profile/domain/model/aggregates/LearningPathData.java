package com.upc.viksadventuresapi.profile.domain.model.aggregates;


import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.converters.IntegerListToJsonConverter;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Quiz;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "learning_path_data", uniqueConstraints = @UniqueConstraint(columnNames = {"profile_id", "quiz_id"}))
public class LearningPathData extends AuditableAbstractAggregateRoot<LearningPathData> {

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Convert(converter = IntegerListToJsonConverter.class)
    private List<Integer> answers;

    @Convert(converter = IntegerListToJsonConverter.class)
    private List<Integer> learningPath;
}
