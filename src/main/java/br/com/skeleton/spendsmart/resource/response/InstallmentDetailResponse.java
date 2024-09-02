package br.com.skeleton.spendsmart.resource.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentDetailResponse {

    private Long amountPaid;

    private Long amountUnpaid;

    private BigDecimal totalPaid;

    private BigDecimal totalUnpaid;

}
