package br.com.skeleton.spendsmart.resource.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class TopPayoffItemsResponse {

    private ExpenseResponse expenseResponse;

    private BigDecimal totalInstallmentComparatorValue;

    private BigDecimal totalComparatorValue;

    private List<String> expenseComparators;

}
