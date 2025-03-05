package com.upc.viksadventuresapi.quiz.application.internal.queryservices;

import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Response;
import com.upc.viksadventuresapi.quiz.domain.model.queries.GetAllResponsesQuery;
import com.upc.viksadventuresapi.quiz.domain.model.queries.GetResponseByIdQuery;
import com.upc.viksadventuresapi.quiz.domain.model.queries.GetResponsesByProfileIdQuery;
import com.upc.viksadventuresapi.quiz.domain.services.ResponseQueryService;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseQueryServiceImpl implements ResponseQueryService {
    public final ResponseRepository responseRepository;

    public ResponseQueryServiceImpl(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public Optional<Response> handle(GetResponseByIdQuery query) {
        return responseRepository.findById(query.responseId());
    }

    @Override
    public List<Response> handle(GetResponsesByProfileIdQuery query) {
        Long profileId = query.profileId();
        return responseRepository.findByProfileId(profileId);
    }

    @Override
    public List<Response> handle(GetAllResponsesQuery query) {
        return responseRepository.findAll();
    }
}
