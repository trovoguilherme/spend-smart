package br.com.skeleton.spendsmart.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseResponse {

    private String username;

    private String name;

    private String value;

    private String status;

    private String type;

    private String paymentType;

    private List<InstallmentResponse> installments;

}
