package com.growandpull.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Column(name = "`required_amount`", nullable = false)
    private BigDecimal requiredAmount;

    @Column(name = "`minimum_investment_amount`", nullable = false)
    private BigDecimal minimumInvestmentAmount;

    @Column(name = "`currency`", nullable = false)
    private Currency currency;

    public Finance(BigDecimal requiredAmount, BigDecimal minimumInvestmentAmount, Currency currency) {
        this.requiredAmount = requiredAmount;
        this.minimumInvestmentAmount = minimumInvestmentAmount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Finance finance)) return false;
        return id.equals(finance.id)
                && requiredAmount.equals(finance.requiredAmount)
                && minimumInvestmentAmount.equals(finance.minimumInvestmentAmount)
                && currency == finance.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requiredAmount, minimumInvestmentAmount, currency);
    }
}
