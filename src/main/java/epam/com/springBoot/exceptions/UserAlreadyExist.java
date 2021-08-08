package epam.com.springBoot.exceptions;

import epam.com.springBoot.model.ErrorType;

public class UserAlreadyExist extends ServiceException {
    private static final String DEFAULT_MESSAGE = "USER IS ALREADY EXIST WITH THIS EMAIL";

    public UserAlreadyExist() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
