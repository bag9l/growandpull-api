package com.growandpull.api.model.entity;

import com.growandpull.api.converter.PeriodConverter;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subscription_type")
public class SubscriptionType {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private SubscriptionTypeIdentifier appIdentifier;

    private String name;

    private BigDecimal price;

    @Convert(converter = PeriodConverter.class)
    private Period period;
}
