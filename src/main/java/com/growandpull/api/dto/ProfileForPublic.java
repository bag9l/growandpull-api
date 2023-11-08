package com.growandpull.api.dto;

import com.growandpull.api.model.Avatar;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ProfileForPublic {
    String fullName;
    String gender;
    String email;
    String phoneNumber;
    LocalDate birth;
    String description;
    Avatar avatar;
}
