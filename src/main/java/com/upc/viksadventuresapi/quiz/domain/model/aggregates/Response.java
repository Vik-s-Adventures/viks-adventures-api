package com.upc.viksadventuresapi.quiz.domain.model.aggregates;

import com.upc.viksadventuresapi.profile.domain.model.aggregates.Profile;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    public Response(){ }

    public Response(Option option, Profile profile) {
        this.option = option;
        this.profile = profile;
    }

    public Long getOptionId(){
        return option.getId();
    }

    public Long getProfileId(){
        return profile.getId();
    }
}
