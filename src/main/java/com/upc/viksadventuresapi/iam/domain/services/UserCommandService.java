package com.upc.viksadventuresapi.iam.domain.services;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.iam.domain.model.commands.DeleteUserByIdCommand;
import com.upc.viksadventuresapi.iam.domain.model.commands.LoginUserGoogleCommand;
import com.upc.viksadventuresapi.iam.domain.model.commands.LoginUserLocalCommand;
import com.upc.viksadventuresapi.iam.domain.model.commands.RegisterUserLocalCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(RegisterUserLocalCommand command);
    Optional<ImmutablePair<User, String>> handle(LoginUserLocalCommand command);
    Optional<ImmutablePair<User, String>> handle(LoginUserGoogleCommand command);
    void handle(DeleteUserByIdCommand command);
}
