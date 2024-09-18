package br.com.skeleton.spendsmart.resource.response;

import br.com.skeleton.spendsmart.entity.enums.HistoryOperationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExpenseHistoryOperationResponse {

    private HistoryOperationType operation;

    private String field;

    private String oldValue;

    private String newValue;

    private LocalDateTime createdAt;

}
