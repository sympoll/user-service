package com.MTAPizza.Sympoll.usermanagementservice.dto.user.description;

import java.util.UUID;

public record UserUpdateDescriptionRequest(
        UUID userId,
        String description
) {
}
