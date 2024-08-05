package com.MTAPizza.Sympoll.usermanagementservice.service.role;

import com.MTAPizza.Sympoll.usermanagementservice.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
