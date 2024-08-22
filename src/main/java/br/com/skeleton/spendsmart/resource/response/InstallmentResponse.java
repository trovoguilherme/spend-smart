package br.com.skeleton.spendsmart.resource.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InstallmentResponse {

    private BigDecimal value;

    private Boolean paid;

}
