package br.com.skeleton.spendsmart.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException() {
        super("Username already exists");
    }

}
