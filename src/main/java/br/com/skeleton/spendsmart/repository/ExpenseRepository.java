package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {



}
