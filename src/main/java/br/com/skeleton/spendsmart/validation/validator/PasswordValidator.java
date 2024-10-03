package br.com.skeleton.spendsmart.validation.validator;

import br.com.skeleton.spendsmart.validation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static br.com.skeleton.spendsmart.utils.RegexUtils.PASSWORD_PATTERN;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null || password.contains(" ") || password.isBlank()) {
            return false;
        }

        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
