package com.MTAPizza.Sympoll.usermanagementservice.validator.exception;

import com.MTAPizza.Sympoll.usermanagementservice.dto.error.GeneralUserError;
import com.MTAPizza.Sympoll.usermanagementservice.dto.error.IllegalUserArgumentError;
import com.MTAPizza.Sympoll.usermanagementservice.dto.error.UserNotFoundError;
import com.MTAPizza.Sympoll.usermanagementservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalUserArgumentError> handlePollValidationException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(new IllegalUserArgumentError(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundError> handlePollValidationException(UserNotFoundError ex, WebRequest request) {
        return new ResponseEntity<>(new UserNotFoundError(ex.message()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles unhandled exceptions only
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralUserError> handleGeneralException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new GeneralUserError(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
