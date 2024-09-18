package br.com.skeleton.spendsmart.resource.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InstallmentResponse {

    private BigDecimal value;

    private Boolean paid;

    private LocalDateTime dueDate;

}
