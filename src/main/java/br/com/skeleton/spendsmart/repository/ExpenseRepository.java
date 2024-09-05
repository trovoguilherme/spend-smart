package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>, CustomExpenseRepository {

    Optional<Expense> findByName(String name);

    boolean existsByName(String name);

}
