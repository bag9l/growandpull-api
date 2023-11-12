package com.growandpull.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


@Getter
@ToString
@AllArgsConstructor
public class UserUpdateRequest {
    private final String fullName;
    private final String login;
    private final String email;
    private final String description;
    private MultipartFile avatar;

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
    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

}