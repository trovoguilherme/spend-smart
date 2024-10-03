package br.com.skeleton.spendsmart.exception;

public class PasswordNotMatchesException extends RuntimeException {

    public PasswordNotMatchesException() {
        super("Old password does not match");
    }

}
