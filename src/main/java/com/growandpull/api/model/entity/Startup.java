package com.growandpull.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.growandpull.api.model.ExistenceTime;
import com.growandpull.api.model.enums.AdStatus;
import com.growandpull.api.model.enums.StartupStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`startup`")
@Entity
public class Startup {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`title`")
    private String title;

    @Lob
    @Column(name = "`description`", length = 5000, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "`status`", nullable = false)
    private StartupStatus status;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private User owner;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "finance_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Finance finance;


    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "details_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private StartupDetails details;

    @Enumerated(EnumType.STRING)
    @Column(name = "`ad_status`", nullable = false)
    private AdStatus adStatus;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = true)
    @JsonManagedReference
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "collaborators",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "startup_id"))
    @JsonBackReference
    private Set<User> collaborators;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    @ToString.Exclude
    @JsonBackReference
    private City city;

    @Transient
    private ExistenceTime existenceTime;

    public Startup(String id,
                   String title,
                   User owner,
                   String description,
                   StartupStatus status,
                   Category category,
                   Finance finance,
                   AdStatus adStatus,
                   Image image,
                   City city) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.status = status;
        this.category = category;
        this.finance = finance;
        this.adStatus = adStatus;
        this.image = image;
        this.city = city;
        this.createdAt = LocalDateTime.now();
    }

    public Startup(String title,
                   User owner,
                   String description,
                   StartupStatus status,
                   Category category,
                   Finance finance,
                   AdStatus adStatus,
                   Image image,
                   City city) {
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.status = status;
        this.category = category;
        this.finance = finance;
        this.adStatus = adStatus;
        this.image = image;
        this.city = city;
        this.createdAt = LocalDateTime.now();
    }

    public Startup(String title, String description, StartupStatus status, Category category, StartupDetails details,
                   User owner, Finance finance, AdStatus adStatus, Image image, City city) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.details = details;
        this.owner = owner;
        this.finance = finance;
        this.adStatus = adStatus;
        this.image = image;
        this.city = city;
        this.createdAt = LocalDateTime.now();
    }

    public ExistenceTime getExistenceTime() {
        return ExistenceTime.createExistenceTimeFrom(createdAt);
    }
}
