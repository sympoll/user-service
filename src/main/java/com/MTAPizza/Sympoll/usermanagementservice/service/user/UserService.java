package com.MTAPizza.Sympoll.usermanagementservice.service.user;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserCreateRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.description.UserUpdateDescriptionRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.email.EmailExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.id.UserIdExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.media.UserUpdateProfileBannerUrlRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.media.UserUpdateProfilePictureUrlRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.username.UsernameExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.exception.UserNotFoundException;
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
    public UserResponse loginUser(UserCreateRequest userCreateRequest) {
        User user = userRepository.findByUsername(userCreateRequest.username());

        if(user == null) {
            validator.validateNewUser(userCreateRequest);
            user = User.builder()
                    .userId(UUID.fromString(userCreateRequest.userId()))
                    .username(userCreateRequest.username())
                    .email(userCreateRequest.email())
                    .build();

            userRepository.save(user);
            log.info("USER: {} was created.", user.getUserId());
            log.info("USER{}: {}", user.getUserId(), user.toString());
        }
        return user.toUserResponse();
    }


    /**
     * Add a profile picture to a user's profile.
     * @param userUpdateProfilePictureUrlRequest Information on the update requested.
     * @return ID of the user that was updated.
     */
    public UUID addProfilePictureUrl(UserUpdateProfilePictureUrlRequest userUpdateProfilePictureUrlRequest) {
        User userToUpdate = userRepository
                .findById(userUpdateProfilePictureUrlRequest.userId())
                .orElseThrow(
                        () -> new UserNotFoundException(userUpdateProfilePictureUrlRequest.userId())
                );

        userToUpdate.setProfilePictureUrl(userUpdateProfilePictureUrlRequest.profilePictureUrl());
        userRepository.save(userToUpdate);

        return userToUpdate.getUserId();
    }

    /**
     * Add a banner picture to a user's profile.
     * @param userUpdateProfileBannerUrlRequest Information on the update requested.
     * @return ID of the user that was updated.
     */
    public UUID addProfileBannerUrl(UserUpdateProfileBannerUrlRequest userUpdateProfileBannerUrlRequest) {
        User userToUpdate = userRepository
                .findById(userUpdateProfileBannerUrlRequest.userId())
                .orElseThrow(
                        () -> new UserNotFoundException(userUpdateProfileBannerUrlRequest.userId())
                );

        userToUpdate.setProfileBannerUrl(userUpdateProfileBannerUrlRequest.bannerPictureUrl());
        userRepository.save(userToUpdate);

        return userToUpdate.getUserId();
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
     * Retrieve a user from the database by his ID.
     * @param userId ID of the user to retrieve.
     * @return The retrieved user's details.
     */
    public UserResponse getUserById(UUID userId){
        validator.checkUserIdExists(userId);
        log.info("Retrieving user by id: {}", userId);
        return userRepository.getReferenceById(userId).toUserResponse();
    }

    /**
     * Retrieve a user from the database by his Username.
     * @param username Username of the user to retrieve.
     * @return The retrieved user's details.
     */
    public UserResponse getUserByUsername(String username) {
        validator.validateUsernameExists(username);
        return userRepository.findByUsername(username).toUserResponse();
    }

    /**
     * Fetch and retrieve multiple user data, by their IDs.
     * @param userIds User IDs to fetch their data.
     * @return A list of DTOs with the users' data.
     */
    public List<UserResponse> getUserListByIds(List<UUID> userIds) {
        validator.checkMultipleUserIdsExist(userIds);
        List<User> users = userRepository.findAllById(userIds);

        return users.stream()
                .map(User::toUserResponse)
                .collect(Collectors.toList());
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
     * Add a profile description to a user's profile.
     * @param userUpdateDescriptionRequest Details on the user to add and the description to update.
     * @return ID of the updated user.
     */
    public UUID updateProfileDescription(UserUpdateDescriptionRequest userUpdateDescriptionRequest) {
        User userToUpdate = userRepository
                .findById(userUpdateDescriptionRequest.userId())
                .orElseThrow(
                        () -> new UserNotFoundException(userUpdateDescriptionRequest.userId())
                );

        userToUpdate.setDescription(userUpdateDescriptionRequest.description());
        userRepository.save(userToUpdate);

        return userToUpdate.getUserId();    }
}
