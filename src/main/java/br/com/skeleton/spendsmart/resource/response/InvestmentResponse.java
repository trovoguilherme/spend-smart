package br.com.skeleton.spendsmart.resource.response;

import br.com.skeleton.spendsmart.entity.Investment;
import br.com.skeleton.spendsmart.entity.enums.Bank;
import br.com.skeleton.spendsmart.entity.enums.InvestmentType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InvestmentResponse {

    private Double balance;

    private InvestmentType investmentType;

    private Bank bankType;

    private Double yield;

    private LocalDate dueDate;

    private LocalDate redemptionDate;

    private Investment.InvestmentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
