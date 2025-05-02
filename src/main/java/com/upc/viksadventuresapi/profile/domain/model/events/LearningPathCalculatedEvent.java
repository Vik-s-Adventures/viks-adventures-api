package com.upc.viksadventuresapi.profile.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class LearningPathCalculatedEvent extends ApplicationEvent {
    private final Long profileId;
    private final Long quizId;
    private final List<Integer> answers;
    private final List<Integer> learningPath;

    public LearningPathCalculatedEvent(Object source, Long profileId, Long quizId, List<Integer> answers, List<Integer> learningPath) {
        super(source);
        this.profileId = profileId;
        this.quizId = quizId;
        this.answers = answers;
        this.learningPath = learningPath;
    }
}
