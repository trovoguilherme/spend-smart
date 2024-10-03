package br.com.skeleton.spendsmart.validation;

import br.com.skeleton.spendsmart.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "O campo não cumpre com os quesitos de sennha";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
