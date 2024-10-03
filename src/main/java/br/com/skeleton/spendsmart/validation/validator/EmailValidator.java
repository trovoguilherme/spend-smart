package br.com.skeleton.spendsmart.validation.validator;

import br.com.skeleton.spendsmart.validation.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static br.com.skeleton.spendsmart.utils.RegexUtils.EMAIL_PATTERN;

public class EmailValidator implements ConstraintValidator<Email, String>  {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        email = email.trim().replaceAll("\\s", "");
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
