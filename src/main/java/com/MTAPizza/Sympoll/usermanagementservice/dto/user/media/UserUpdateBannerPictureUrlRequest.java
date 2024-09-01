package com.MTAPizza.Sympoll.usermanagementservice.dto.user.media;

import java.util.UUID;

public record UserUpdateBannerPictureUrlRequest(
        UUID userId,
        String bannerPictureUrl
) {
}
