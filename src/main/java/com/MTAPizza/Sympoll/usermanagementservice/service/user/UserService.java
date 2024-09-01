package com.MTAPizza.Sympoll.usermanagementservice.service.user;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserCreateRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.email.EmailExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.id.UserIdExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.id.UserIdResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.username.UsernameExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.username.UserGroupMemberResponse;
import com.MTAPizza.Sympoll.usermanagementservice.model.user.User;
import com.MTAPizza.Sympoll.usermanagementservice.password.PasswordHasher;
import com.MTAPizza.Sympoll.usermanagementservice.repository.user.UserRepository;
import com.MTAPizza.Sympoll.usermanagementservice.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final Validator validator;

    /**
     * Create and add a user to the database.
     * @param userCreateRequest Details of the user to add.
     * @return The user that was added to the database.
     */
    public UserResponse createUser(UserCreateRequest userCreateRequest){
        validator.validateNewUser(userCreateRequest);
        User user = User.builder()
                .username(userCreateRequest.username())
                .password(PasswordHasher.hashPassword(userCreateRequest.password()))
                .email(userCreateRequest.email())
                .build();

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
        validator.checkUserIdExists(userId);
        log.info("Retrieving user by id: {}", userId);
        return userRepository.getReferenceById(userId).toUserResponse();
    }

    /**
     * Delete a user from the database.
     * @param userId ID of the user to delete.
     * @return the ID of the user deleted.
     */
    public UUID deleteUserById(UUID userId){
        validator.checkUserIdExists(userId);
        log.info("Deleting user by id: {}", userId);
        userRepository.deleteById(userId);
        return userId;
    }

    /**
     * Verify if given user ID exists in the database
     * @param userId User ID to check
     * @return A DTO holds a boolean field
     */
    public UserIdExistsResponse checkUserIdExists(UUID userId){
        return new UserIdExistsResponse(userRepository.existsById(userId));
    }

    /**
     * Verify if given username already exists in the database.
     * @param username Username to check.
     * @return A DTO holds a boolean field.
     */
    public UsernameExistsResponse checkUsernameExists(String username){
        return new UsernameExistsResponse(userRepository.existsByUsername(username));
    }

    /**
     * Verify if given email already exists in the database.
     * @param email email to check.
     * @return A DTO holds a boolean field.
     */
    public EmailExistsResponse checkEmailExists(String email){
        return new EmailExistsResponse(userRepository.existsByEmail(email));
    }

    /**
     * Fetch and retrieve a list of usernames by their ids.
     * @param userIdList Given user ids.
     * @return A list of DTO with the user id and the username.
     */
    public List<UserGroupMemberResponse> getUsernames(List<UUID> userIdList) {
        validator.checkMultipleUserIdsExist(userIdList);
        List<User> users = userRepository.findAllById(userIdList);

        return users.stream()
                .map(user -> new UserGroupMemberResponse(user.getUserId(), user.getUsername()))
                .collect(Collectors.toList());
    }

    public UserIdResponse getUserIdByUsername(String username) {
        validator.checkUsernameExists(username);
        // "orElse(null)" in order to avoid Optional<User>. This method won't be invoked if the username not exists.
        return new UserIdResponse(userRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst().orElse(null).getUserId());
    }

}
