package br.com.skeleton.spendsmart.resource.request;

import br.com.skeleton.spendsmart.entity.enums.Bank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBankAccount {

    @Positive
    private Double balance;

    private Bank bankType;

}
