package com.upc.viksadventuresapi.quiz.domain.model.valueobjects;

import com.upc.viksadventuresapi.quiz.domain.model.enums.PerformanceDetail;
import jakarta.persistence.Embeddable;

@Embeddable
public record Performance(int value, String description) {

    public Performance {
        PerformanceDetail detail = PerformanceDetail.fromValue(value);
        description = detail.getDescription();
    }

    public Performance(int value) {
        this(value, PerformanceDetail.fromValue(value).getDescription());
    }
}