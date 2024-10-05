package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.BankAccount;
import br.com.skeleton.spendsmart.entity.Wallet;
import br.com.skeleton.spendsmart.exception.NotFoundException;
import br.com.skeleton.spendsmart.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserService userService;

    public BankAccount deposit(BankAccount bankAccount) {
        final Wallet wallet = userService.findByUsername().getWallet();

        return bankAccountRepository.findByBankTypeAndWalletId(bankAccount.getBankType(), wallet.getId())
                .map(existingAccount -> depositBalanceExistingAccount(existingAccount, bankAccount.getBalance()))
                .orElseGet(() -> createNewBankAccount(bankAccount, wallet));
    }

    public BankAccount updateBalance(BankAccount bankAccount) {
        final Wallet wallet = userService.findByUsername().getWallet();

        return bankAccountRepository.findByBankTypeAndWalletId(bankAccount.getBankType(), wallet.getId())
                .map(existingAccount -> updateExistingAccount(existingAccount, bankAccount.getBalance()))
                .orElseThrow(() -> new NotFoundException("Bank account not found"));
    }

    private BankAccount depositBalanceExistingAccount(BankAccount existingAccount, Double depositAmount) {
        existingAccount.deposit(depositAmount);
        return bankAccountRepository.save(existingAccount);
    }

    private BankAccount createNewBankAccount(BankAccount bankAccount, Wallet wallet) {
        bankAccount.setWallet(wallet);
        return bankAccountRepository.save(bankAccount);
    }

    private BankAccount updateExistingAccount(BankAccount existingAccount, Double newBalance) {
        existingAccount.setBalance(newBalance);
        return bankAccountRepository.save(existingAccount);
    }

}
