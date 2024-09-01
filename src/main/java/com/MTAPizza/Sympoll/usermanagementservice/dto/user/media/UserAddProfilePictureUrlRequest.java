package com.MTAPizza.Sympoll.usermanagementservice.dto.user.media;

import java.util.UUID;

public record UserAddProfilePictureUrlRequest(
        UUID userId,
        String profilePictureUrl
) {
}
