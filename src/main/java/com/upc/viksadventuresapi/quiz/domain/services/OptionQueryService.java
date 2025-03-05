package com.upc.viksadventuresapi.quiz.domain.services;

import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Option;
import com.upc.viksadventuresapi.quiz.domain.model.queries.GetAllOptionsQuery;
import com.upc.viksadventuresapi.quiz.domain.model.queries.GetOptionByIdQuery;
import com.upc.viksadventuresapi.quiz.domain.model.queries.GetOptionsByQuestionIdQuery;

import java.util.List;
import java.util.Optional;

public interface OptionQueryService {
    Optional<Option> handle(GetOptionByIdQuery query);
    List<Option> handle(GetAllOptionsQuery query);
    List<Option> handle(GetOptionsByQuestionIdQuery query);
}
