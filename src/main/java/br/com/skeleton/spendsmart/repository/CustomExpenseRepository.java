package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.PaymentType;

import java.util.List;

public interface CustomExpenseRepository {

    List<Expense> findAllByFilter(ExpenseStatus expenseStatus, ExpenseType expenseType, PaymentType paymentType);

}
