package com.MTAPizza.Sympoll.usermanagementservice.service.user;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserCreateRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.MTAPizza.Sympoll.usermanagementservice.model.user.User;
import com.MTAPizza.Sympoll.usermanagementservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    /**
     * Create and add a user to the database.
     * @param userCreateRequest Details of the user to add.
     * @return The user that was added to the database.
     */
    public UserResponse createUser(UserCreateRequest userCreateRequest){
        User user = User.builder()
                .username(userCreateRequest.username())
                .password(hashPassword(userCreateRequest.password()))
                .email(userCreateRequest.email())
                .build();

        // TODO: Validation for the user object
        userRepository.save(user);
        log.info("USER: {} was created.", user.getUserId());
        return user.toUserResponse();
    }

    /**
     * Retrieves all users from the repository and maps them to USerResponse DTOs.
     * @return List of UserResponse DTOs containing details of all users.
     */
    public List<UserResponse> getAllUsers() {
        log.info("Retrieving all users in database...");
        return userRepository
                .findAll()
                .stream()
                .map(User::toUserResponse)
                .toList();
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
