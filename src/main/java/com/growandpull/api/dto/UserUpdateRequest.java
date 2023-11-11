package com.growandpull.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.growandpull.api.model.Avatar;
import com.growandpull.api.model.StartupStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdateRequest {
    private final String fullName;

    private final String login;
    private final String email;
    private final String description;

    @JsonCreator
    public UserUpdateRequest(
            @JsonProperty("fullName") String fullName,
            @JsonProperty("login") String login,
            @JsonProperty("email") String email,
            @JsonProperty("description") String description) {

        this.fullName = fullName;
        this.login = login;
        this.email = email;
        this.description = description;
    }

}
