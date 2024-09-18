package br.com.skeleton.spendsmart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INSTALLMENT")
public class Installment {

    @Id
    @Column(name = "ID_INSTALLMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_EXPENSE")
    private Expense expense;

    @Column(name = "INSTALLMENT_VALUE")
    private BigDecimal value;

    @Column(name = "IS_PAID")
    private Boolean paid;

    @Column(name = "DUE_DATE")
    private LocalDateTime dueDate;

    public void pay() {
        paid = true;
    }

}
