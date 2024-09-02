package br.com.skeleton.spendsmart.entity;

import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.PaymentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EXPENSE")
public class Expense {

    @Id
    @Column(name = "ID_EXPENSE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EXPENSE_VALUE")
    private Double value;

    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    private List<Installment> installments;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    @Column(name = "PAYMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public void setStatusToPending() {
        this.status = ExpenseStatus.PENDING;
    }

    public BigDecimal getTotalUnpaidAmount() {
        return installments.stream()
                .filter(installment -> !installment.getPaid())
                .map(Installment::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPaidAmount() {
        return installments.stream()
                .filter(Installment::getPaid)
                .map(Installment::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getUnpaidInstallmentsCount() {
        return installments.stream()
                .filter(installment -> !installment.getPaid())
                .count();
    }

    public Long getPaidInstallmentsCount() {
        return installments.stream()
                .filter(Installment::getPaid)
                .count();
    }

}
