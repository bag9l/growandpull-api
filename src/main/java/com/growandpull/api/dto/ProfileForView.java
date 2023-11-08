package com.growandpull.api.dto;

import com.growandpull.api.model.Avatar;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ProfileForView {
    String fullName;
    String gender;
    String email;
    String phoneNumber;
    LocalDate birth;
    String description;
    String password;
    Avatar avatar;
    private byte[] avatarImageData; // Додайте поле для даних аватарки

}
