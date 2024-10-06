package br.com.skeleton.spendsmart.resource.request;

import br.com.skeleton.spendsmart.entity.enums.Bank;
import br.com.skeleton.spendsmart.entity.enums.InvestmentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvestmentRequest {

    @Positive
    private Double balance;

    @NotNull
    private InvestmentType investmentType;

    @NotNull
    private Bank bankType;

    @Positive
    private Double yield;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private LocalDate redemptionDate;

}
