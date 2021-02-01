package sstu.diploma.ivory.security.annotation;

import sstu.diploma.ivory.security.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * pay attention to @Documented
 * needs to enforce custom constraints based on user group
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {

    String message() default "The password does not meet the requirements";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
