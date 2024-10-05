package br.com.skeleton.spendsmart.entity;

import br.com.skeleton.spendsmart.entity.enums.Bank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

    @Id
    @Column(name = "ID_BANK_ACCOUNT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BALANCE")
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "BANK_TYPE", unique = true)
    private Bank bankType;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "ID_WALLET")
    private Wallet wallet;

    public void deposit(Double balance) {
        if (balance > 0) {
            this.balance += balance;
        }
    }

    public void withdraw(Double amountToWithdraw) {
        if (balance >= amountToWithdraw) {
            this.balance -= amountToWithdraw;
        }
    }
}
