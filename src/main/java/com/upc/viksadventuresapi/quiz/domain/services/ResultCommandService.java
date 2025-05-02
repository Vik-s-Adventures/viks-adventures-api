package com.upc.viksadventuresapi.quiz.domain.services;

import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Result;
import com.upc.viksadventuresapi.quiz.domain.model.commands.CreateResultCommand;
import com.upc.viksadventuresapi.quiz.domain.model.commands.DeleteResultByIdCommand;
import com.upc.viksadventuresapi.quiz.domain.model.commands.UpdateResultCommand;

import java.util.Optional;

public interface ResultCommandService {
    Optional<Result> handle(CreateResultCommand command);
    Optional<Result> handle(UpdateResultCommand command);
    void handle(DeleteResultByIdCommand command);
}
