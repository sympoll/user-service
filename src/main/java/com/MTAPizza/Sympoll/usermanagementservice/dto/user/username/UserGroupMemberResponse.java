package com.MTAPizza.Sympoll.usermanagementservice.dto.user.username;

import java.util.UUID;

public record UserGroupMemberResponse(
       UUID userId,
       String username
) {
}
