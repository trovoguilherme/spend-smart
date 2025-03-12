package br.com.skeleton.spendsmart.model;

import br.com.skeleton.spendsmart.entity.Expense;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class TopPayoffItem {

    private Expense expense;

    private BigDecimal totalInstallmentComparatorValue;

    private BigDecimal totalComparatorValue;

    private List<Expense> comparatorExpenses;

    public TopPayoffItem(Expense expense, List<Expense> comparatorExpenses) {
        this.expense = expense;
        this.totalInstallmentComparatorValue = comparatorExpenses.stream()
                .map(e -> e.getInstallments().isEmpty() ? BigDecimal.ZERO :
                        e.getInstallments().get(0).getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalComparatorValue = comparatorExpenses.stream().map(Expense::getTotalUnpaidAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.comparatorExpenses = comparatorExpenses;
    }
}
