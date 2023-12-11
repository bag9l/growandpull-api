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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "app_identifier", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private SubscriptionTypeIdentifier appIdentifier;

    @Column(name = "`name`", nullable = false)
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "finance_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Finance finance;

    @Convert(converter = PeriodConverter.class)
    private Period period;
}
