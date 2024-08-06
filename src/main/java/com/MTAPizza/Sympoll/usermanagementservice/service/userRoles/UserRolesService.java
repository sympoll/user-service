package com.MTAPizza.Sympoll.usermanagementservice.service.userRoles;

import com.MTAPizza.Sympoll.usermanagementservice.repository.userRoles.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRolesService {
    private final UserRolesRepository userRolesRepository;
}
