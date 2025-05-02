package com.upc.viksadventuresapi.iam.application.internal.queryservices;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.iam.domain.model.queries.GetAllUsersQuery;
import com.upc.viksadventuresapi.iam.domain.model.queries.GetUserByIdQuery;
import com.upc.viksadventuresapi.iam.domain.services.UserQueryService;
import com.upc.viksadventuresapi.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }
}