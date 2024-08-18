package br.com.skeleton.spendsmart.entity;

import br.com.skeleton.spendsmart.exception.ValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "INSTALLMENT")
public class Installment {

    @Id
    @Column(name = "ID_INSTALLMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "EXPENSE_VALUE")
    private Double value;

    @Column(name = "AMOUNT_PAID")
    private Integer amountPaid;

    @Column(name = "AMOUNT_PENDING")
    private Integer amountPending;

    @Column(name = "PENDING_VALUE")
    private Double pendingValue;

    @Column(name = "PAID_VALUE")
    private Double paidValue;

    public void computePaidValue() {
        paidValue = value * amountPaid;
    }

    public void computePendingValue() {
        pendingValue = value * amountPending;
    }

    public void validateAmount(double expenseValue) {
        if (value > expenseValue) {
            throw new ValidationException("Installment Value is greater than Expense Value");
        }

        if ((amountPaid + amountPending) * value > expenseValue) {
            throw new ValidationException("Amount Paid + Pending is greater than Expense Value");
        }

        if (amountPaid * value > expenseValue) {
            throw new ValidationException("Amount Paid is greater than the Expense Value");
        }

        if (amountPending * value > expenseValue) {
            throw new ValidationException("Amount Pending is greater than the Expense Value");
        }
    }

}
