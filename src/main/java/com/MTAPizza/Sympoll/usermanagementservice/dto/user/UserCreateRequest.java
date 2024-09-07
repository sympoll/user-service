package com.MTAPizza.Sympoll.usermanagementservice.dto.user;

public record UserCreateRequest(
        String userId,
        String username,
        String email
) {
}
