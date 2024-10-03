package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
