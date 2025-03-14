package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.User;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.PaymentType;
import br.com.skeleton.spendsmart.exception.BusinessException;
import br.com.skeleton.spendsmart.exception.InstallmentAllPaidException;
import br.com.skeleton.spendsmart.exception.NotFoundException;
import br.com.skeleton.spendsmart.model.TopPayoffItem;
import br.com.skeleton.spendsmart.repository.ExpenseRepository;
import br.com.skeleton.spendsmart.utils.CacheName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final InstallmentService installmentService;
    private final ExpenseHistoryService expenseHistoryService;
    private final UserDetailsServiceImpl userDetailsService;

    public List<Expense> findAll(ExpenseStatus expenseStatus, ExpenseType expenseType, PaymentType paymentType) {
        return expenseRepository.findAllByFilter(userDetailsService.getActualUser().getUsername(), expenseStatus, expenseType, paymentType);
    }

    @Cacheable(value = CacheName.EXPENSES, key = "#name")
    public Expense findByName(String name) {
        return expenseRepository.findByNameAndUserUsername(name, userDetailsService.getActualUser().getUsername()).orElseThrow(() -> new NotFoundException("Name not found"));
    }

    public Expense findByIdAndUserUsername(final Long id) {
        return expenseRepository.findByIdAndUserUsername(id, userDetailsService.getActualUser().getUsername()).orElseThrow(() -> new NotFoundException("Id not found"));
    }

    @Transactional
    public Expense save(Expense expense) {
        User user = userDetailsService.getActualUser();

        existsByName(expense.getName());

        expense.setStatusToPending();
        expense.setUser(user);

        Expense expenseSaved = expenseRepository.save(expense);

        if (expense.getInstallments() != null) {
            expense.getInstallments().forEach(installment -> installment.setExpense(expense));
            installmentService.saveAll(expense.getInstallments());
        }
        return expenseSaved;
    }

    @Transactional
    public List<Expense> saveAll(List<Expense> expenses) {
        User user = userDetailsService.getActualUser();
        List<Expense> expenseSaved = new ArrayList<>();

        expenses.forEach(e -> {
            existsByName(e.getName());

            e.setStatusToPending();
            e.setUser(user);

            expenseSaved.add(expenseRepository.save(e));

            if (e.getInstallments() != null) {
                e.getInstallments().forEach(installment -> installment.setExpense(e));
                installmentService.saveAll(e.getInstallments());
            }
        });


        return expenseSaved;
    }

    @Transactional
    public Expense update(Long id, String changeAgent, Expense expense) {
        existsByName(expense.getName());
        Expense expenseFound = findByIdAndUserUsername(id);

        Expense expenseOld = new Expense(expenseFound);

        expenseFound.setName(expense.getName());
        expenseFound.setType(expense.getType());
        expenseFound.setPaymentType(expense.getPaymentType());

        expenseHistoryService.save(expenseOld, expenseFound, changeAgent);

        return expenseRepository.save(expenseFound);
    }

    public Expense pay(Long id) {
        Expense expense = findByIdAndUserUsername(id);

        validateIfAlreadyPaid(expense);

        if (!expense.getInstallments().isEmpty()) {
            handleInstallmentPayments(expense);
        } else {
            expense.setStatusToPaid();
        }
        return expenseRepository.save(expense);
    }

    @Transactional
    public void deleteById(Long id) {
        Expense expense = findByIdAndUserUsername(id);

        if (!expense.getInstallments().isEmpty()) {
            installmentService.deleteByExpense(expense);
        }
        expenseRepository.deleteById(id);
    }

    public List<TopPayoffItem> getTopPayoffItem() {
        final List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream().map(e -> {
            AtomicReference<Double> valorRestante = new AtomicReference<>(e.getTotalUnpaidAmount().doubleValue());

            List<Expense> expensesComparators = expenses.stream()
                    .filter(other -> !other.equals(e))
                    .sorted(Comparator.comparing(Expense::getTotalUnpaidAmount))
                    .filter(other -> {
                        double novoValor = valorRestante.get() - other.getTotalUnpaidAmount().doubleValue();

                        if (novoValor >= 0) {
                            valorRestante.set(novoValor);
                            return true;
                        }
                        return false;
                    }).toList();
            return new TopPayoffItem(e, expensesComparators);
        }).sorted(Comparator.comparing(TopPayoffItem::getTotalInstallmentComparatorValue)
                        .thenComparing(TopPayoffItem::getTotalComparatorValue).reversed()).toList();
    }

    private void validateIfAlreadyPaid(Expense expense) {
        if (ExpenseStatus.PAID.equals(expense.getStatus())) {
            throw new InstallmentAllPaidException();
        }
    }

    private void handleInstallmentPayments(Expense expense) {
        installmentService.pay(expense.getInstallments());

        if (installmentService.allInstallmentsPaid(expense.getInstallments())) {
            expense.setStatusToPaid();
        }
    }

    private void existsByName(String name) {
        if (expenseRepository.existsByName(name)) {
            throw new BusinessException("Expense name already exists");
        }
    }

}
