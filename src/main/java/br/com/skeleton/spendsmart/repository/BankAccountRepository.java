package br.com.skeleton.spendsmart.repository;

import br.com.skeleton.spendsmart.entity.BankAccount;
import br.com.skeleton.spendsmart.entity.enums.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByBankTypeAndWalletId(Bank bank, Long id);

}
