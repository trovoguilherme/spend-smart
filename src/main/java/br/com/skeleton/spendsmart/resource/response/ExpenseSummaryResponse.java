package br.com.skeleton.spendsmart.resource.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSummaryResponse {

    private BigDecimal totalUnpaid;

    private List<ExpenseDetailResponse> expenseDetailResponseList;

}
