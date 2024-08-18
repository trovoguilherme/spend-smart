package br.com.skeleton.spendsmart.entity;

import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSTALLMENT", foreignKey = @ForeignKey(name = "FK_EXPENSE_INSTALLMENT"))
    private Installment installment;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

}
