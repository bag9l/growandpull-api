package com.growandpull.api.model;

import jakarta.persistence.*;
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
    private String fullName;
    private String gender;
    private String login;
    private String email;
    private String phoneNumber;
    private LocalDate birth;
    private String description;
    private String password;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
