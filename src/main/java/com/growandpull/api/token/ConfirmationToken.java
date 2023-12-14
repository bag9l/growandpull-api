package com.growandpull.api.token;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.growandpull.api.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString()
@Table(name = "`confirmation_token`")
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    private Boolean isRevoked;

    private Boolean isExpired;

    private LocalDate createdAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonBackReference
    private User user;


    public ConfirmationToken(String token, Boolean isRevoked, Boolean isExpired, User user) {
        this.token = token;
        this.isRevoked = isRevoked;
        this.isExpired = isExpired;
        this.user = user;
        this.createdAt=LocalDate.now();
    }

    public ConfirmationToken(Integer id, String token, Boolean isRevoked, Boolean isExpired, User user) {
        this.id = id;
        this.token = token;
        this.isRevoked = isRevoked;
        this.isExpired = isExpired;
        this.user = user;
        this.createdAt=LocalDate.now();
    }

    public ConfirmationToken() {
        this.createdAt=LocalDate.now();
    }
}
