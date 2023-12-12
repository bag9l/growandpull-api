package com.growandpull.api.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.growandpull.api.dto.education.EducationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    private final String phoneNumber;
    private final String about;
    private final int cityId;
    private final EducationDto education;
    private final LocalDate birthday;
    private final String experience;
    private MultipartFile avatar;

    @JsonCreator
    public UserUpdateRequest(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("about") String about,
            @JsonProperty("birthday") LocalDate birthday,
            @JsonProperty("phoneNumber") String phoneNumber,
            @JsonProperty("cityId") int cityId,
            @JsonProperty("education") EducationDto education,
            @JsonProperty("experience") String experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.about = about;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.cityId = cityId;
        this.education = education;
        this.experience = experience;

    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

}