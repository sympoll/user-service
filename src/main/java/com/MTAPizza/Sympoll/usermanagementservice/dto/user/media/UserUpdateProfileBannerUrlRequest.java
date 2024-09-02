package com.MTAPizza.Sympoll.usermanagementservice.dto.user.media;

import java.util.UUID;

public record UserUpdateProfileBannerUrlRequest(
        UUID userId,
        String bannerPictureUrl
) {
}
