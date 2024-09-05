package br.com.skeleton.spendsmart.resource.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpenseHistoryDetailResponse {

    private String field;

    private String oldValue;

    private String newValue;

}
