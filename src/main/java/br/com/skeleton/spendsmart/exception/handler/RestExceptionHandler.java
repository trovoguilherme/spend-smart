package br.com.skeleton.spendsmart.exception.handler;

import br.com.skeleton.spendsmart.exception.BusinessException;
import br.com.skeleton.spendsmart.exception.InstallmentAllPaidException;
import br.com.skeleton.spendsmart.exception.InsufficientBalanceWithdrawException;
import br.com.skeleton.spendsmart.exception.NotFoundException;
import br.com.skeleton.spendsmart.exception.ResponseException;
import br.com.skeleton.spendsmart.exception.UsernameAlreadyExistsException;
import br.com.skeleton.spendsmart.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseException> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringJoiner errors = new StringJoiner(", ");

        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });

        return ResponseEntity.badRequest().body(ResponseException.builder()
                .exceptionMessage(errors.toString())
                .build());
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ResponseException> NotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseException.builder()
                .exceptionMessage(exception.getMessage())
                .build());
    }

    @ExceptionHandler({
            InsufficientBalanceWithdrawException.class,
            BusinessException.class,
            UsernameAlreadyExistsException.class,
            InstallmentAllPaidException.class,
            ValidationException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ResponseException> handleBadRequestExceptions(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                ResponseException.builder()
                        .exceptionMessage(exception.getMessage())
                        .build());
    }

}
