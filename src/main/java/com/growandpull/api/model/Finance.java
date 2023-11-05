package com.growandpull.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`finance`")
@Entity
public class Finance {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`required_amount`")
    private BigDecimal requiredAmount;

    @Column(name = "`minimum_investment_amount`")
    private BigDecimal minimumInvestmentAmount;

    @Column(name = "`currency`")
    private Currency currency;

    public Finance(BigDecimal requiredAmount, BigDecimal minimumInvestmentAmount, Currency currency) {
        this.requiredAmount = requiredAmount;
        this.minimumInvestmentAmount = minimumInvestmentAmount;
        this.currency = currency;
    }
}
