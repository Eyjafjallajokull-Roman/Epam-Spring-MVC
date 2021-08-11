package epam.com.springBoot.validator.annotation;

import epam.com.springBoot.validator.TimeValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = TimeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeConstraint {
    String message() default "Time doesn't match ";

    String endTime();

    String startTime();

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        TimeConstraint[] value();
    }
}
