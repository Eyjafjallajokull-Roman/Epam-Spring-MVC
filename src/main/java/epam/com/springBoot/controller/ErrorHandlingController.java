package epam.com.springBoot.controller;

import epam.com.springBoot.exceptions.CustomError;
import epam.com.springBoot.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError handleServiceException(ServiceException ex, HandlerMethod hm) {
        return new CustomError(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
    }


}
