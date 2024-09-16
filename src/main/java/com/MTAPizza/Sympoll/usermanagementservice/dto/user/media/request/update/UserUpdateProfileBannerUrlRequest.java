package com.MTAPizza.Sympoll.usermanagementservice.dto.user.media.request.update;

import java.util.UUID;

public record UserUpdateProfileBannerUrlRequest(
        UUID userId,
        String bannerPictureUrl
) {
}
