package com.MTAPizza.Sympoll.usermanagementservice.dto.user.media.response.update;

import java.util.UUID;

public record UpdateProfilePictureUrlResponse(
        UUID userId,
        String profilePictureUrl
) {
}
