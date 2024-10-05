package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.BankAccount;
import br.com.skeleton.spendsmart.entity.Wallet;
import br.com.skeleton.spendsmart.exception.InsufficientBalanceWithdrawException;
import br.com.skeleton.spendsmart.exception.NotFoundException;
import br.com.skeleton.spendsmart.repository.BankAccountRepository;
import br.com.skeleton.spendsmart.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final WalletRepository walletRepository;
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

    public BankAccount withdraw(BankAccount bankAccount) {
        final Wallet wallet = userService.findByUsername().getWallet();

        return bankAccountRepository.findByBankTypeAndWalletId(bankAccount.getBankType(), wallet.getId())
                .map(existingAccount -> withdrawBalanceFromExistingAccount(existingAccount, bankAccount.getBalance()))
                .orElseThrow(() -> new NotFoundException("Bank account not found"));
    }

    private BankAccount withdrawBalanceFromExistingAccount(BankAccount existingAccount, Double amountToWithdraw) {
        if (existingAccount.getBalance() < amountToWithdraw) {
            throw new InsufficientBalanceWithdrawException("Insufficient balance for withdrawal");
        }

        Double previousBalance = existingAccount.getBalance();
        existingAccount.withdraw(amountToWithdraw);
        updateWallet(existingAccount.getWallet(), previousBalance, existingAccount.getBalance());
        return bankAccountRepository.save(existingAccount);
    }

    private BankAccount depositBalanceExistingAccount(BankAccount existingAccount, Double depositAmount) {
        Double previousBalance = existingAccount.getBalance();
        existingAccount.deposit(depositAmount);
        updateWallet(existingAccount.getWallet(), previousBalance, existingAccount.getBalance());
        return bankAccountRepository.save(existingAccount);
    }

    private BankAccount createNewBankAccount(BankAccount bankAccount, Wallet wallet) {
        bankAccount.setWallet(wallet);
        updateWallet(bankAccount.getWallet(), 0.0,bankAccount.getBalance());
        return bankAccountRepository.save(bankAccount);
    }

    private BankAccount updateExistingAccount(BankAccount existingAccount, Double newBalance) {
        Double previousBalance = existingAccount.getBalance();
        existingAccount.setBalance(newBalance);
        updateWallet(existingAccount.getWallet(), previousBalance, existingAccount.getBalance());
        return bankAccountRepository.save(existingAccount);
    }

    private void updateWallet(Wallet wallet, Double previousBalance, Double newBalance) {
        Double balanceChange = newBalance - previousBalance;
        wallet.setBalance(wallet.getBalance() + balanceChange);
        walletRepository.save(wallet);
    }

}
