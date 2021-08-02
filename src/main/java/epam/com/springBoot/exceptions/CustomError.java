package epam.com.springBoot.exceptions;

import epam.com.springBoot.model.ErrorType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomError {
    private final String message;
    private final ErrorType errorType;
    private final LocalDateTime localDateTime;


}
