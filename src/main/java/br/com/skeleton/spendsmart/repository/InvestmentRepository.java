package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
