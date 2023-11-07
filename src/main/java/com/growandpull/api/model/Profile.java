package com.growandpull.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "profile")
@Entity
public class Profile {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phoneNumber;
    private LocalDate birth;
    private String description;
    private String password;



}
