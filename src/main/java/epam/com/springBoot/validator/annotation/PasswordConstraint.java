package epam.com.springBoot.validator.annotation;

import epam.com.springBoot.validator.PasswordValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Fields don`t much!";

    String field();

    String fieldMatch();

    Class<?>[] groups() default {};
    Class<?>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        PasswordConstraint[] value();
    }
}
