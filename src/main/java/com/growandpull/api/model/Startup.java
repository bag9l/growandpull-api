package com.growandpull.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private User owner;
    @Lob
    @Column(name = "`description`", length = 5000, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "`status`", nullable = false)
    private StartupStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "finance_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Finance finance;

    @Enumerated(EnumType.STRING)
    @Column(name = "`ad_status`", nullable = false)
    private AdStatus adStatus;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = true)
    @JsonManagedReference
    private Image image;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

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
                   LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.status = status;
        this.category = category;
        this.finance = finance;
        this.adStatus = adStatus;
        this.image = image;
        this.createdAt = createdAt;
    }

    public Startup(String title,
                   User owner,
                   String description,
                   StartupStatus status,
                   Category category,
                   Finance finance,
                   AdStatus adStatus,
                   Image image,
                   LocalDateTime createdAt) {
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.status = status;
        this.category = category;
        this.finance = finance;
        this.adStatus = adStatus;
        this.image = image;
        this.createdAt = createdAt;
    }

    public ExistenceTime getExistenceTime() {
        return ExistenceTime.createExistenceTimeFrom(createdAt);
    }
}
