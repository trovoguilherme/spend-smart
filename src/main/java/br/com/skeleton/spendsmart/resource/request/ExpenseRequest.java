package br.com.skeleton.spendsmart.resource.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExpenseRequest {

    @NotBlank(message = "Name não pode ser vazio ou nullo")
    private String name;

    @Positive(message = "Value deve ser positivo")
    private Double value;

    @Valid
    private InstallmentRequest installment;

    @NotBlank(message = "Type não pode ser vazio ou nullo")
    private String type;

}
