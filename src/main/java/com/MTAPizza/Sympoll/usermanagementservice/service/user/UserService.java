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
import java.util.UUID;

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
        log.info("USER{}: {}", user.getUserId(), user.toString());
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

    /**
     * Retrieve a user from the database.
     * @param userId ID of the user to retrieve.
     * @return The retrieved user's details.
     */
    public UserResponse getUserById(UUID userId){
        log.info("Retrieving user by id: {}", userId);
        return userRepository.getReferenceById(userId).toUserResponse();
    }

    /**
     * Delete a user from the database.
     * @param userId ID of the user to delete.
     * @return the ID of the user deleted.
     */
    public UUID deleteUserById(UUID userId){
        log.info("Deleting user by id: {}", userId);
        userRepository.deleteById(userId);
        return userId;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
