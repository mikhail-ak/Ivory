package sstu.diploma.ivory.security.validator;

import sstu.diploma.ivory.security.annotation.PasswordConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
