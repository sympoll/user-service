package com.MTAPizza.Sympoll.usermanagementservice.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
     UUID userId,
     String username,
     String email,
     String profilePictureUrl,
     String bannerPictureUrl,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
     LocalDateTime timeCreated
) {
}
