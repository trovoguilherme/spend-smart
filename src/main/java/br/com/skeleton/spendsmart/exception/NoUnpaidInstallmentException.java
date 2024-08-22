package br.com.skeleton.spendsmart.exception;

import lombok.Getter;

@Getter
public class NoUnpaidInstallmentException extends RuntimeException {

    private final String message;

    public NoUnpaidInstallmentException() {
        this.message = "No unpaid installments";
    }

}
