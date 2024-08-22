package br.com.skeleton.spendsmart.resource.response;

import lombok.Data;

import java.util.List;

@Data
public class ExpenseResponse {

    private String name;

    private String value;

    private List<InstallmentResponse> installments;

    private String status;

    private String type;

}
