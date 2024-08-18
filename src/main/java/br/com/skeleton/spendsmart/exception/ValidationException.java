package br.com.skeleton.spendsmart.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationException extends RuntimeException {

    private final String message;

}
