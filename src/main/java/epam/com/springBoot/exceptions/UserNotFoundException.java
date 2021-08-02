package epam.com.springBoot.exceptions;

import epam.com.springBoot.model.ErrorType;

public class UserNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "User is not found";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType(){
        return ErrorType.VALIDATION_ERROR_TYPE;
    }


}
