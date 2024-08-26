package com.MTAPizza.Sympoll.usermanagementservice.dto.user.username;

import java.util.List;
import java.util.UUID;

public record UsernameRequest(
        List<UUID> userIdList
) {
}
