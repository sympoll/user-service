package com.MTAPizza.Sympoll.usermanagementservice.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("User with ID '" + userId + "' was not found");
    }

    public UserNotFoundException(UUID userId) {
        super("User with ID '" + userId + "' was not found");
    }
}
