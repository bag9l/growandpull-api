package com.growandpull.api.token;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.growandpull.api.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`confirmation_token`")
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String confirmationToken;

    private Boolean isRevoked;

    private Boolean isExpired;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonBackReference
    private User user;


    public ConfirmationToken(String confirmationToken, Boolean isRevoked, Boolean isExpired, User user) {
        this.confirmationToken = confirmationToken;
        this.isRevoked = isRevoked;
        this.isExpired = isExpired;
        this.user = user;
    }
}
