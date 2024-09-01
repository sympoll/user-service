package com.MTAPizza.Sympoll.usermanagementservice.dto.user.media;

import java.util.UUID;

public record UserAddBannerPictureUrlRequest(
        UUID userId,
        String bannerPictureUrl
) {
}
