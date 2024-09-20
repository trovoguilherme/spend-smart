package br.com.skeleton.spendsmart.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseDetailResponse {

    private String name;

    private BigDecimal value;

    private InstallmentDetailResponse installmentDetailResponse;

    private String type;

    private String paymentType;

}
