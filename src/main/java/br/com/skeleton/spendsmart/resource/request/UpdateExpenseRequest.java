package br.com.skeleton.spendsmart.resource.request;

import lombok.Data;

@Data
public class UpdateExpenseRequest {

    private String name;

    private String type;

    private String paymentType;

}
