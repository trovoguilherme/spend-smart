package br.com.skeleton.spendsmart.resource.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest {

    @NotBlank(message = "Name não pode ser vazio ou nullo")
    private String name;

    @Positive(message = "Value deve ser positivo")
    private BigDecimal value;

    @Valid
    private InstallmentRequest installment;

    @NotBlank(message = "Type não pode ser vazio ou nullo")
    private String type;

}
