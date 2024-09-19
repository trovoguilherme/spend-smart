package br.com.skeleton.spendsmart.resource.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseHistoryResponse {

    private Long expenseId;

    private String name;

    private List<ExpenseHistoryOperationResponse> expenseHistoryOperationResponses;

    private String changeAgent;

}
