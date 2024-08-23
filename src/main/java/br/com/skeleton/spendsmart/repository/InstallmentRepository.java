package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    List<Installment> findAllByExpense_Id(Long id);

}
