package br.com.skeleton.spendsmart.exception;

public class InstallmentAllPaidException extends RuntimeException {

    public InstallmentAllPaidException() {
        super("Installment is all paid");
    }
}
