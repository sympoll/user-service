package com.MTAPizza.Sympoll.usermanagementservice.dto.user.username;

import java.util.UUID;

public record UsernameResponse(
       UUID userId,
       String username
) {
}
