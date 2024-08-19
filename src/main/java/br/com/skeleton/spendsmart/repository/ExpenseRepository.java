package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e LEFT JOIN FETCH e.installment WHERE e.id = :id")
    Optional<Expense> findByIdWithInstallment(@Param("id") Long id);

}
