package com.MTAPizza.Sympoll.usermanagementservice.service.user.roles;

import com.MTAPizza.Sympoll.usermanagementservice.repository.user.roles.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRolesService {
    private final UserRolesRepository userRolesRepository;
}
