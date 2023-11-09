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

    @Column(name = "`collected_amount`", nullable = false)
    private BigDecimal collectedAmount = BigDecimal.ZERO;

    @Column(name = "`required_amount`", nullable = false)
    private BigDecimal requiredAmount;

    @Column(name = "`minimum_investment_amount`", nullable = false)
    private BigDecimal minimumInvestmentAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "`currency`", nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "`investment_type`", nullable = false)
    private InvestmentType investmentType;


    public Finance(BigDecimal requiredAmount,
                   BigDecimal minimumInvestmentAmount,
                   Currency currency,
                   InvestmentType investmentType) {
        this.requiredAmount = requiredAmount;
        this.minimumInvestmentAmount = minimumInvestmentAmount;
        this.currency = currency;
        this.investmentType = investmentType;
    }

    @PrePersist
    void preInsert() {
        if (this.collectedAmount == null) {
            this.collectedAmount = BigDecimal.ZERO;
        }
        if (this.investmentType.equals(InvestmentType.SOLE_INVESTOR)) {
            this.minimumInvestmentAmount = this.requiredAmount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Finance finance)) return false;
        return id.equals(finance.id) && collectedAmount.equals(finance.collectedAmount) && requiredAmount.equals(finance.requiredAmount) && minimumInvestmentAmount.equals(finance.minimumInvestmentAmount) && currency == finance.currency && investmentType == finance.investmentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collectedAmount, requiredAmount, minimumInvestmentAmount, currency, investmentType);
    }
}
