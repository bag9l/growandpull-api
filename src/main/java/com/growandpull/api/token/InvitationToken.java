package com.growandpull.api.token;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.growandpull.api.model.entity.Startup;
import com.growandpull.api.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString()
@Table(name = "`invitation_token`")
@Entity
public class InvitationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    private Boolean isRevoked;

    private Boolean isExpired;

    private LocalDate createdAt;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonBackReference
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "startup_id")
    @ToString.Exclude
    @JsonBackReference
    private Startup startup;



    public InvitationToken(String token, Boolean isRevoked, Boolean isExpired, User user,Startup startup) {
        this.token = token;
        this.isRevoked = isRevoked;
        this.isExpired = isExpired;
        this.user = user;
        this.startup = startup;
        this.createdAt = LocalDate.now();
    }

    public InvitationToken(Integer id, String token, Boolean isRevoked, Boolean isExpired, User user, Startup startup) {
        this.id = id;
        this.token = token;
        this.isRevoked = isRevoked;
        this.isExpired = isExpired;
        this.user = user;
        this.startup = startup;
        this.createdAt = LocalDate.now();
    }

    public InvitationToken() {
        this.createdAt = LocalDate.now();
    }
}
