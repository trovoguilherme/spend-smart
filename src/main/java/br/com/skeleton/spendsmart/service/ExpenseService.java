package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.exception.BusinessException;
import br.com.skeleton.spendsmart.exception.NotFoundException;
import br.com.skeleton.spendsmart.repository.ExpenseRepository;
import br.com.skeleton.spendsmart.resource.response.ExpenseDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;
    private final InstallmentService installmentService;

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Expense findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Name not found"));
    }

    public Expense findById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
    }

    @Transactional
    public Expense save(Expense expense) {
        existsByName(expense.getName());

        expense.setStatusToPending();

        Expense expenseSaved = repository.save(expense);

        if (expense.getInstallments() != null) {
            expense.getInstallments().forEach(installment -> installment.setExpense(expense));
            installmentService.saveAll(expense.getInstallments());
        }

        return expenseSaved;
    }

    public Expense update(Long id, Expense expense) {
        existsByName(expense.getName());
        Expense expenseFound = findById(id);

        expenseFound.setName(expense.getName());
        expenseFound.setValue(expense.getValue());
        expenseFound.setType(expense.getType());

        return repository.save(expenseFound);
    }

    public Expense pay(Long id) {
        Expense expense = findById(id);

        if (!expense.getInstallments().isEmpty()) {
            List<Installment> installments = installmentService.pay(expense.getInstallments());

            expense.setInstallments(installments);

            return expense;
        } else {
            //TODO Salvar no histórico
            return expense;
        }
    }

    private void existsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Expense name already exists");
        }
    }

}
