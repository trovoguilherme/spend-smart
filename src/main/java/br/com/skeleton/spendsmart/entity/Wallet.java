package br.com.skeleton.spendsmart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WALLET")
public class Wallet {

    @Id
    @Column(name = "ID_WALLET")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BALANCE")
    private Double balance;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER)
    private List<BankAccount> bankAccounts;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_WALLET")
    private List<Investment> investments;

    @OneToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private User user;

}
