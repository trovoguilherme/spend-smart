package br.com.skeleton.spendsmart.entity;

import br.com.skeleton.spendsmart.entity.enums.HistoryOperationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EXPENSE_HISTORY")
public class ExpenseHistory {

    @Id
    @Column(name = "ID_EXPENSE_HISTORY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_EXPENSE")
    private Expense expense;

    @Column(name = "CHANGE_AGENT")
    private String changeAgent;

    @Enumerated(EnumType.STRING)
    @Column(name = "OPERATION")
    private HistoryOperationType operation;

    @Column(name = "FIELD")
    private String field;

    @Column(name = "OLD_VALUE")
    private String oldValue;

    @Column(name = "NEW_VALUE")
    private String newValue;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    public ExpenseHistory(Expense expense, HistoryOperationType operation, String field, String oldValue, String newValue) {
        this.expense = expense;
        this.operation = operation;
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

}
