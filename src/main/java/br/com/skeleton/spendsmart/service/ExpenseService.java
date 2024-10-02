package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.PaymentType;
import br.com.skeleton.spendsmart.exception.BusinessException;
import br.com.skeleton.spendsmart.exception.NotFoundException;
import br.com.skeleton.spendsmart.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;
    private final InstallmentService installmentService;
    private final ExpenseHistoryService expenseHistoryService;
    private final UserDetailsServiceImpl userDetailsService;

    public List<Expense> findAll(ExpenseStatus expenseStatus, ExpenseType expenseType, PaymentType paymentType) {
        return repository.findAllByFilter(expenseStatus, expenseType, paymentType);
    }

    public Expense findByName(String name, Authentication authentication) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

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

    @Transactional
    public Expense update(Long id, String changeAgent, Expense expense) {
        existsByName(expense.getName());
        Expense expenseFound = findById(id);

        Expense expenseOld = new Expense(expenseFound);

        expenseFound.setName(expense.getName());
        expenseFound.setType(expense.getType());
        expenseFound.setPaymentType(expense.getPaymentType());

        expenseHistoryService.save(expenseOld, expenseFound, changeAgent);

        return repository.save(expenseFound);
    }

    public Expense pay(Long id) {
        Expense expense = findById(id);

        if (!expense.getInstallments().isEmpty()) {
            List<Installment> installments = installmentService.pay(expense.getInstallments());
            expense.setInstallments(installments);

            boolean allPaid = installments.stream().allMatch(Installment::getPaid);

            if (allPaid) {
                expense.setStatusToPaid();
                repository.save(expense);
            }

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

    @Transactional
    public void deleteById(Long id) {
        Expense expense = findById(id);

        if (!expense.getInstallments().isEmpty()) {
            installmentService.deleteByExpense(expense);
        }

        repository.deleteById(id);
    }

}
