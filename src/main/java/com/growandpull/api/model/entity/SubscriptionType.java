package com.growandpull.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.growandpull.api.converter.PeriodConverter;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscription_type")
public class SubscriptionType {

    @Id
    @Enumerated(EnumType.STRING)
    private SubscriptionTypeIdentifier id;

    @Column(name = "`name`", nullable = false)
    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_id", referencedColumnName = "id")
    private Finance price;

    @Convert(converter = PeriodConverter.class)
    private Period period;
}
