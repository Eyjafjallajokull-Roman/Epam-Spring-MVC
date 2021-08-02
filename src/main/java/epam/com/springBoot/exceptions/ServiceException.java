package epam.com.springBoot.exceptions;

import epam.com.springBoot.model.ErrorType;
import lombok.Data;

@Data
public abstract class ServiceException extends RuntimeException {
    private ErrorType errorType;

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR_TYPE;
    }
}
