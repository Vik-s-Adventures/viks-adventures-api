package com.upc.viksadventuresapi.quiz.domain.model.commands;

public record UpdateResultCommand (
        Long profileId,
        Long quizId
){
}
