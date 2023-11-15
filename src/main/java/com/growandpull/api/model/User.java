package com.growandpull.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.growandpull.api.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`login`", nullable = false, unique = true)
    private String login;

    @Column(name = "`description`", nullable = true)
    private String description;

    @Column(name = "`birth`", nullable = false)
    private LocalDate birth;

    @Column(name = "`email`", nullable = false, unique = true)
    private String email;

    @Column(name = "`password`", nullable = false)
    private String password;

    @Column(name = "`fullName`", nullable = false)
    private String fullName;

    @Column(name = "`role`", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id", nullable = true)
    @JsonManagedReference
    private Avatar avatar;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    @JsonManagedReference
    private Set<Startup> startups;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Token> tokens;


    private Boolean isExpired;

    private Boolean isLocked;

    private Boolean isCredentialsExpired;

    private Boolean isEnabled;


    public User(String login,
                String password,
                String fullName,
                String email,
                Role role,
                Avatar avatar,
                List<Token> tokens) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.avatar = avatar;
        this.tokens = tokens;
        this.isExpired = false;
        this.isLocked = false;
        this.isCredentialsExpired = false;
        this.isEnabled = true;
    }

    public User(String login, String email, String password, String fullName, Role role, Avatar avatar, Set<Startup> startups, List<Token> tokens) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.avatar = avatar;
        this.startups = startups;
        this.tokens = tokens;
        this.isExpired = false;
        this.isLocked = false;
        this.isCredentialsExpired = false;
        this.isEnabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "login = " + login + ", " +
                "email = " + email + ", " +
                "password = " + password + ", " +
                "fullName = " + fullName + ", " +
                "role = " + role + ", " +
                "avatar = " + avatar + ", " +
                "isExpired = " + isExpired + ", " +
                "isLocked = " + isLocked + ", " +
                "isCredentialsExpired = " + isCredentialsExpired + ", " +
                "isEnabled = " + isEnabled + ")";
    }
}
