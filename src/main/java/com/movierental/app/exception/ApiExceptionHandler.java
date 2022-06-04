package com.movierental.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {CustomerAlreadyExistsException.class})
    ResponseEntity<ApiException> handleCustomerAlreadyExists(CustomerAlreadyExistsException exception, WebRequest request) {
        ApiException apiException = new ApiException(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
