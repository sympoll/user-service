package com.MTAPizza.Sympoll.usermanagementservice.dto.user.media;

import java.util.UUID;

public record UserUpdateProfilePictureUrlRequest(
        UUID userId,
        String profilePictureUrl
) {
}
