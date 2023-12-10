package com.growandpull.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.growandpull.api.model.enums.Role;
import com.growandpull.api.token.Token;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`about`")
    private String about;

    @Column(name = "`birthday`")
    private LocalDate birthday;

    @Column(name = "`email`", nullable = false, unique = true)
    private String email;

    @Column(name = "`phone_number`", length = 10, unique = true)
    private String phoneNumber;

    @Column(name = "`password`", nullable = false)
    private String password;

    @Column(name = "`firstName`", nullable = false)
    private String firstName;

    @Column(name = "`lastName`", nullable = false)
    private String lastName;

    @Column(name = "`role`", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id", nullable = true)
    @JsonManagedReference
    private Avatar avatar;

    @Lob
    @Column(name = "experience", length = 1000)
    private String experience;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    @ToString.Exclude
    @JsonBackReference
    private City city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "education_id")
    @ToString.Exclude
    @JsonBackReference
    private Education education;

    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true
    )
    @ToString.Exclude
    @JsonManagedReference
    private Set<Startup> startups;

    @ManyToMany
    @JoinTable(
            name = "favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "startup_id"))
    @JsonBackReference
    private Set<Startup> favoriteStartups;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Token> tokens;

    private Boolean isExpired;

    private Boolean isLocked;

    private Boolean isCredentialsExpired;

    private Boolean isEnabled;

    public User(String email, String password, String firstName, String lastName, Role role, Avatar avatar, Set<Startup> startups, Set<Startup> favoriteStartups, List<Token> tokens) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.avatar = avatar;
        this.favoriteStartups = favoriteStartups;
        this.startups = startups;
        this.tokens = tokens;
        this.isExpired = false;
        this.isLocked = false;
        this.isCredentialsExpired = false;
        this.isEnabled = true;
    }

    public User(String email, String password, String firstName, String lastName, Role role, Avatar avatar, Set<Startup> startups, List<Token> tokens) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.avatar = avatar;
        this.startups = startups;
        this.tokens = tokens;
        this.isExpired = false;
        this.isLocked = false;
        this.isCredentialsExpired = false;
        this.isEnabled = false;
    }


    public User(String email, String password, String firstName, String lastName, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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
        return email;
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
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", avatar=" + avatar +
                ", isExpired=" + isExpired +
                ", isLocked=" + isLocked +
                ", isCredentialsExpired=" + isCredentialsExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }

}
