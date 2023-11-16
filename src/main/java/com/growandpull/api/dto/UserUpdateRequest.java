package com.growandpull.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Getter
@ToString
@AllArgsConstructor
public class UserUpdateRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String aboutUser;
    private final LocalDate birth;
    private MultipartFile avatar;

    @JsonCreator
    public UserUpdateRequest(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("aboutUser") String aboutUser,
            @JsonProperty("birth") LocalDate birth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.aboutUser = aboutUser;
        this.birth = birth;

    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

}