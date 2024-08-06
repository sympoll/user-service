package com.MTAPizza.Sympoll.usermanagementservice.service.user;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserCreateRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.MTAPizza.Sympoll.usermanagementservice.model.user.User;
import com.MTAPizza.Sympoll.usermanagementservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponse createUser(UserCreateRequest userCreateRequest){
        User user = User.builder()
                .username(userCreateRequest.username())
                .password(encodePassword(userCreateRequest.password()))
                .email(userCreateRequest.email())
                .build();

        // TODO: Validation for the user object
        userRepository.save(user);
        log.info("USER: {} was created.", user.getUserId());
        return user.toUserResponse();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
