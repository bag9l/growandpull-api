package com.growandpull.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.growandpull.api.converter.DateConverter;
import com.growandpull.api.converter.PeriodConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    @ToString.Exclude
    @JsonBackReference
    private SubscriptionType type;

    private LocalDate startedAt;

    private LocalDate expiresAt;

    @Transient
    private Boolean isExpired;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "owner_id")
    @ToString.Exclude
    @JsonBackReference
    private User owner;

    public Subscription(String id, SubscriptionType type) {
        this.id = id;
        this.type = type;
        this.startedAt = LocalDate.now();
        this.expiresAt = LocalDate.now().plus(type.getPeriod());
    }

    public Boolean getIsExpired() {
        return !expiresAt.isAfter(LocalDate.now());
    }
}
