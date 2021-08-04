package epam.com.springBoot.exceptions;

import epam.com.springBoot.model.ErrorType;

public class NoSuchActivityExist extends ServiceException{

    private static final String DEFAULT_MESSAGE = "No such Activity exist";

    public NoSuchActivityExist() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
