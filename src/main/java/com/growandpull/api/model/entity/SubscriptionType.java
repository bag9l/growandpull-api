package com.growandpull.api.model.entity;

import com.growandpull.api.converter.PeriodConverter;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subscriprion_type")
public class SubscriptionType {

    @Id
    @Enumerated(EnumType.STRING)
    private SubscriptionTypeIdentifier id;

    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_id", referencedColumnName = "id")
    private Finance price;

    @Convert(converter = PeriodConverter.class)
    private Period period;
}
