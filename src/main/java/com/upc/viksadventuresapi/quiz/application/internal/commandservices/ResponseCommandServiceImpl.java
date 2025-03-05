package com.upc.viksadventuresapi.quiz.application.internal.commandservices;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import com.upc.viksadventuresapi.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Option;
import com.upc.viksadventuresapi.quiz.domain.model.aggregates.Response;
import com.upc.viksadventuresapi.quiz.domain.model.commands.CreateResponseCommand;
import com.upc.viksadventuresapi.quiz.domain.model.commands.DeleteOptionByIdCommand;
import com.upc.viksadventuresapi.quiz.domain.model.commands.DeleteResponseByIdCommand;
import com.upc.viksadventuresapi.quiz.domain.services.ResponseCommandService;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.OptionRepository;
import com.upc.viksadventuresapi.quiz.infrastructure.persistence.jpa.repositories.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseCommandServiceImpl implements ResponseCommandService {
    private final ResponseRepository responseRepository;
    private final OptionRepository optionRepository;
    private final ProfileRepository profileRepository;

    public ResponseCommandServiceImpl(ResponseRepository responseRepository, OptionRepository optionRepository, ProfileRepository profileRepository) {
        this.responseRepository = responseRepository;
        this.optionRepository = optionRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Response> handle(CreateResponseCommand command) {
        // Verify if the option exists
        Optional<Option> optionalOption = optionRepository.findById(command.optionId());
        if (optionalOption.isEmpty()) {
            throw new IllegalArgumentException("Option with ID " + command.optionId() + " does not exist.");
        }
        Option option = optionalOption.get();

        // Verify if the profile exists
        Optional<Profile> optionalProfile = profileRepository.findById(command.profileId());
        if (optionalProfile.isEmpty()) {
            throw new IllegalArgumentException("Profile with ID " + command.profileId() + " does not exist.");
        }
        Profile profile = optionalProfile.get();

        // Create the response
        Response response = new Response(option, profile);
        return Optional.of(responseRepository.save(response));

    }

    @Override
    public void handle(DeleteResponseByIdCommand command) {
        if (!responseRepository.existsById(command.responseId())) {
            throw new IllegalArgumentException("Response with ID " + command.responseId() + " does not exist.");
        }
        try {
            responseRepository.deleteById(command.responseId());
        } catch (Exception e) {
            throw new RuntimeException("Error deleting response with ID " + command.responseId());
        }
    }
}
