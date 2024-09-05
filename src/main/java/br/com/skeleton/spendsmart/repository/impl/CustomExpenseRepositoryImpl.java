package br.com.skeleton.spendsmart.repository.impl;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.PaymentType;
import br.com.skeleton.spendsmart.repository.CustomExpenseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomExpenseRepositoryImpl implements CustomExpenseRepository {

    private final EntityManager entityManager;

    @Override
    public List<Expense> findAllByFilter(ExpenseStatus expenseStatus, ExpenseType expenseType, PaymentType paymentType) {
        StringBuilder jpql = new StringBuilder("SELECT e FROM Expense e");

        List<String> whereCause = new ArrayList<>();
        Map<String, Object> parameters = new HashMap<>();

        if (expenseStatus != null) {
            whereCause.add(" e.status = :status");
            parameters.put("status", expenseStatus);
        }

        if (expenseType != null) {
            whereCause.add(" e.type = :type");
            parameters.put("type", expenseType);
        }

        if (paymentType != null) {
            whereCause.add(" e.paymentType = :paymentType");
            parameters.put("paymentType", paymentType);
        }

        if (!whereCause.isEmpty()) {
            jpql.append(" WHERE ").append(String.join(" AND ", whereCause));
        }

        TypedQuery<Expense> query = entityManager.createQuery(jpql.toString(), Expense.class);
        parameters.forEach(query::setParameter);

        return query.getResultList();
    }

}
