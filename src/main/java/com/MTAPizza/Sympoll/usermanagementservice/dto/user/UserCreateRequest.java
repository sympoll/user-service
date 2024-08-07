package com.MTAPizza.Sympoll.usermanagementservice.dto.user;

public record UserCreateRequest(
        String username,
        String password,
        String email
) {
}
