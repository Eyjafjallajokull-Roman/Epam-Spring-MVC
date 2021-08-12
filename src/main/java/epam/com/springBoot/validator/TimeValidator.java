package epam.com.springBoot.validator;

import epam.com.springBoot.validator.annotation.TimeConstraint;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.sql.Timestamp;

public class TimeValidator implements ConstraintValidator<TimeConstraint, Object> {

    private String endTimeField;
    private String startTimeField;
    private String typeOfActivityField;


    @Override
    public void initialize(TimeConstraint constraintAnnotation) {
        endTimeField = constraintAnnotation.endTime();
        startTimeField = constraintAnnotation.startTime();
        typeOfActivityField = constraintAnnotation.typeOfActivity();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        final Field startTimeField = value.getClass().getDeclaredField(this.startTimeField);
        startTimeField.setAccessible(true);

        final Field endTimeField = value.getClass().getDeclaredField(this.endTimeField);
        endTimeField.setAccessible(true);

        final Field typeOfActivityField = value.getClass().getDeclaredField(this.typeOfActivityField);
        typeOfActivityField.setAccessible(true);

        final Timestamp startTime = (Timestamp) startTimeField.get(value);
        final Timestamp endTime = (Timestamp) endTimeField.get(value);
        final String typeOfActivity = (String) typeOfActivityField.get(value);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (typeOfActivity.equals("TIME_TRACKER"))
            return startTime != null && startTime.after(now);

        else if (typeOfActivity.equals("REMINDER"))
            return endTime != null && now.before(endTime);

        else {
            if (endTime == null || startTime == null) {
                return false;
            }
            return startTime.after(now) && startTime.before(endTime);
        }
    }


}
