package epam.com.springBoot.exceptions;

import epam.com.springBoot.model.ErrorType;

public class NoSuchUserException extends ServiceException{

    private static final String DEFAULT_MESSAGE = "User by this email is not exist";

    public NoSuchUserException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType(){
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
