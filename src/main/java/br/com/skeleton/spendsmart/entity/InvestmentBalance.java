package br.com.skeleton.spendsmart.entity;

import br.com.skeleton.spendsmart.entity.enums.Bank;
import br.com.skeleton.spendsmart.entity.enums.InvestmentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INVESTMENT_BALANCE")
public class InvestmentBalance {

    @Id
    @Column(name = "ID_INVESTMENT_BALANCE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BALANCE")
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "INVESTMENT_TYPE")
    private InvestmentType investmentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "BANK_TYPE")
    private Bank bankType;

    @Column(name = "YIELD")
    private Double yield;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "REDEMPTION_DATE")
    private LocalDate redemptionDate;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

}
