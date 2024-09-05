package br.com.skeleton.spendsmart.resource.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ExpenseHistoryResponse {

    private Long expenseId;

    private String operation;

    List<ExpenseHistoryDetailResponse> expenseHistoryDetailResponses;

    private LocalDateTime createdAt;

}
