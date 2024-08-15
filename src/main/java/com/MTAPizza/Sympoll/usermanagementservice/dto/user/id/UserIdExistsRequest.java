package com.MTAPizza.Sympoll.usermanagementservice.dto.user.id;

import java.util.UUID;

public record UserIdExistsRequest(
        UUID userId
) {
}
