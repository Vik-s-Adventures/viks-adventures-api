package com.upc.viksadventuresapi.quiz.domain.model.aggregates;

import com.upc.viksadventuresapi.quiz.domain.model.commands.CreateQuestionCommand;
import com.upc.viksadventuresapi.quiz.domain.model.valueobjects.ImageUrl;
import com.upc.viksadventuresapi.quiz.domain.model.valueobjects.Performance;
import com.upc.viksadventuresapi.quiz.domain.model.valueobjects.QuestionText;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Question extends AuditableAbstractAggregateRoot<Question> {
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Embedded
    private Performance performance;

    @Embedded
    private QuestionText questionText;

    @Embedded
    private ImageUrl imageUrl;

    public Question(Quiz quiz, CreateQuestionCommand command) {
        this(
                quiz,
                new Performance(command.performance()),
                new QuestionText(command.questionText()),
                new ImageUrl(command.imageUrl())
        );
    }
}