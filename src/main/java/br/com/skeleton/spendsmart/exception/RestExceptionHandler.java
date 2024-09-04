package br.com.skeleton.spendsmart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ResponseException> businessException(BusinessException exception) {
        return ResponseEntity.badRequest().body(ResponseException.builder()
                .exceptionMessage(exception.getMessage())
                .build());
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResponseException> validationException(ValidationException exception) {
        return ResponseEntity.badRequest().body(ResponseException.builder()
                .exceptionMessage(exception.getMessage())
                .build());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ResponseException> illegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(ResponseException.builder()
                .exceptionMessage(exception.getMessage())
                .build());
    }

}
