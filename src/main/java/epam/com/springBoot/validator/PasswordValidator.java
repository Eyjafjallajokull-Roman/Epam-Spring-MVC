package epam.com.springBoot.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(PasswordConstraint passwordConstraint) {
        this.field = passwordConstraint.field();
        this.fieldMatch = passwordConstraint.fieldMatch();
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }


}
