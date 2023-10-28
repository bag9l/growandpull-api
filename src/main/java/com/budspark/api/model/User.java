package com.budspark.api.model;

import com.budspark.api.token.Token;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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

    @Column(name = "`login`", unique = true)
    private String login;

    @Column(name = "`email`", unique = true)
    private String email;

    @Column(name = "`password`")
    private String password;

    @Column(name = "`fullName`")
    private String fullName;

    @Column(name = "`role`")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id", nullable = true)
    @JsonManagedReference
    private Avatar avatar;

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
