package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.ExpenseHistory;
import br.com.skeleton.spendsmart.entity.enums.HistoryOperationType;
import br.com.skeleton.spendsmart.repository.ExpenseHistoryRepository;
import lombok.AllArgsConstructor;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseHistoryService {

    private final ExpenseHistoryRepository repository;

    public void save(Expense oldExpense, Expense newExpense, final String changeAgent) {
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(oldExpense, newExpense);

        diff.getChanges().forEach(change -> {
            if (change instanceof ValueChange valueChange) {
                String propertyName = valueChange.getPropertyName();

                if (valueChange.getLeft() != null) {
                    String oldValue = valueChange.getLeft().toString();
                    String newValue = valueChange.getRight().toString();

                    repository.save(ExpenseHistory.builder()
                            .expense(newExpense)
                            .changeAgent(changeAgent)
                            .operation(HistoryOperationType.UPDATED)
                            .field(propertyName)
                            .oldValue(oldValue)
                            .newValue(newValue)
                            .build());
                }
            }
        });

    }

    public List<ExpenseHistory> findByName(final String name) {
        return repository.findAllByExpense_Name(name);
    }

    public List<ExpenseHistory> findAll() {
        return repository.findAll();
    }

}
