package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.ExpenseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistory, Long> {
}
