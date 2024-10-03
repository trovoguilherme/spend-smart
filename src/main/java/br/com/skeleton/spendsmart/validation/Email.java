package br.com.skeleton.spendsmart.validation;

import br.com.skeleton.spendsmart.validation.validator.EmailValidator;
import br.com.skeleton.spendsmart.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default "O campo não cumpre com os requesitos de email, xxxxxxx@xxx.xxx";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
