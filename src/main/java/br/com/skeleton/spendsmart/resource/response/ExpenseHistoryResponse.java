package br.com.skeleton.spendsmart.resource.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpenseHistoryResponse {

    private Long expenseId;

    private String name;

    private ExpenseHistoryOperationResponse expenseHistoryOperationResponse;

    private String changeAgent;

}
