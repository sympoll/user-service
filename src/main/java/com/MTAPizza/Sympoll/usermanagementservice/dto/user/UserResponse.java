package com.MTAPizza.Sympoll.usermanagementservice.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
     UUID userID,
     String username,
     String passwordHash,
     String email,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
     LocalDateTime timeCreated
) {
}
