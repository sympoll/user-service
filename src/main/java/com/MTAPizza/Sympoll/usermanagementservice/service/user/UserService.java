package com.MTAPizza.Sympoll.usermanagementservice.service.user;

import com.MTAPizza.Sympoll.usermanagementservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
