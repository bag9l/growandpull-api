package com.growandpull.api.model.entity;

import com.growandpull.api.model.enums.Currency;
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

    @Column(name = "`amount`", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "`currency`", nullable = false)
    private Currency currency;


    public Finance(BigDecimal amount,
                   Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Finance finance)) return false;
        return id.equals(finance.id) && amount.equals(finance.amount) && currency == finance.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency);
    }
}
