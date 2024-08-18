package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.exception.BusinessException;
import br.com.skeleton.spendsmart.repository.ExpenseRepository;
import br.com.skeleton.spendsmart.repository.InstallmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository purchaseRepository;
    private final InstallmentRepository installmentRepository;

    public List<Expense> findAll() {
        return purchaseRepository.findAll();
    }

    public Expense save(Expense expense) {
        if (expense.getInstallment().getValue() > expense.getValue()) {
            throw new BusinessException("O valor da parcela é maior que o valor total");
        }

        expense.getInstallment().setPendingValue(expense.getInstallment().getValue() * expense.getInstallment().getPendingAmount());
        expense.setStatus(ExpenseStatus.PENDING);

        Installment installment = installmentRepository.save(expense.getInstallment());
        expense.setInstallment(installment);

        return purchaseRepository.save(expense);
    }

    public Expense update(final Long id) {
        Expense expense = purchaseRepository.findById(id).get();
        expense.setName("alterado");
        return purchaseRepository.save(expense);
    }

}
