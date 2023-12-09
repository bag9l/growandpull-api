package com.growandpull.api.token;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.growandpull.api.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`token`")
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    private Boolean isRevoked;

    private Boolean isExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonBackReference
    private User user;

    public Token(String token, Boolean isRevoked, Boolean isExpired, User user) {
        this.token = token;
        this.isRevoked = isRevoked;
        this.isExpired = isExpired;
        this.user = user;
    }
}
