package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.exception.BusinessException;
import br.com.skeleton.spendsmart.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;
    private final InstallmentService installmentService;

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Expense findById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException("Id not found"));
    }

    @Transactional
    public Expense save(Expense expense) {
        Expense expenseSaved = repository.save(expense);
        expense.getInstallments().forEach(installment -> installment.setExpense(expense));
        installmentService.saveAll(expense.getInstallments());

        return expenseSaved;
    }

    public Expense pay(Long id) {
        Expense expense = findById(id);

        if (expense.getInstallments() != null) {
            List<Installment> installments = installmentService.pay(expense.getInstallments());

            expense.setInstallments(installments);

            return expense;
        } else {
            //TODO Salvar no histórico
            return expense;
        }
    }
}
