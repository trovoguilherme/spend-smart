package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.exception.BusinessException;
import br.com.skeleton.spendsmart.repository.ExpenseRepository;
import br.com.skeleton.spendsmart.repository.InstallmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final InstallmentRepository installmentRepository;

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Transactional
    public Expense save(Expense expense) {
        if (expense.getInstallment() != null) {

            expense.getInstallment().validateAmount(expense.getValue());
            expense.getInstallment().computePendingValue();
            expense.getInstallment().computePaidValue();

            Installment installment = installmentRepository.save(expense.getInstallment());
            expense.setInstallment(installment);
        }

        expense.setStatus(ExpenseStatus.PENDING);

        return expenseRepository.save(expense);
    }

    public Expense update(final Long id) {
        Expense expense = expenseRepository.findById(id).get();
        expense.setName("alterado");
        return expenseRepository.save(expense);
    }

    public Expense pay(Long id) {
        Expense expense = expenseRepository.findByIdWithInstallment(id)
                .orElseThrow(() -> new BusinessException("Id not found"));
        expense.pay();

        return expenseRepository.save(expense);
    }

}
