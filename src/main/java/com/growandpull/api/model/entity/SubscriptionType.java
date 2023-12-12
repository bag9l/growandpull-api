package com.growandpull.api.model.entity;

import com.growandpull.api.converter.PeriodConverter;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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

    @Enumerated(EnumType.STRING)
    private SubscriptionTypeIdentifier appIdentifier;

    @Column(name = "`name`", nullable = false)
    private String name;

    @Lob
    @Column(name = "description", length = 5000)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_id", referencedColumnName = "id")
    private Finance price;

    @Convert(converter = PeriodConverter.class)
    private Period period;
}
