package br.com.skeleton.spendsmart.resource.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class InstallmentRequest {

    @Positive
    private Double value;

    @PositiveOrZero
    private Integer amountPaid;

    @Positive
    private Integer amountPending;

}
