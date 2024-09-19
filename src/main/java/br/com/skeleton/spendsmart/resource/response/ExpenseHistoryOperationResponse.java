package br.com.skeleton.spendsmart.resource.response;

import br.com.skeleton.spendsmart.entity.enums.HistoryOperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseHistoryOperationResponse {

    private HistoryOperationType operation;

    private String field;

    private String oldValue;

    private String newValue;

    private LocalDateTime createdAt;

}
