package com.MTAPizza.Sympoll.usermanagementservice.controller;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserCreateRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.email.EmailExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.id.UserIdExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.media.UserUpdateBannerPictureUrlRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.media.UserUpdateProfilePictureUrlRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.id.UserIdResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.username.UsernameExistsResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.username.UserGroupMemberResponse;
import com.MTAPizza.Sympoll.usermanagementservice.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class ServiceController {
    private final UserService userService;

    /**
     * Create a new user to save in the database.
     * @param userCreateRequest Information of the user to be created.
     * @return The created user, as it is saved in the database.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest){
        log.info("Received request to create a user");
        log.debug("User received to create: {}", userCreateRequest);
        return userService.createUser(userCreateRequest);
    }

    /**
     * Save a profile picture for a user
     * @param userUpdateProfilePictureUrlRequest Information on the update to perform.
     * @return The updated user's ID.
     */
    @PostMapping("/profile-picture-url")
    @ResponseStatus(HttpStatus.OK)
    public UUID updateProfilePictureUrl(@RequestBody UserUpdateProfilePictureUrlRequest userUpdateProfilePictureUrlRequest){
        log.info("Received request to save a profile picture url");
        log.debug("Request received to add profile picture url: {}", userUpdateProfilePictureUrlRequest);
        return userService.addProfilePictureUrl(userUpdateProfilePictureUrlRequest);
    }

    /**
     * Save a banner picture for a user
     * @param userUpdateBannerPictureUrlRequest Information on the update to perform.
     * @return The updated user's ID.
     */
    @PostMapping("/profile-banner-url")
    @ResponseStatus(HttpStatus.OK)
    public UUID updateBannerPictureUrl(@RequestBody UserUpdateBannerPictureUrlRequest userUpdateBannerPictureUrlRequest){
        log.info("Received request to save a banner picture url");
        log.debug("Request received to add banner picture url: {}", userUpdateBannerPictureUrlRequest);
        return userService.addBannerPictureUrl(userUpdateBannerPictureUrlRequest);
    }

    /**
     * Fetch all the users currently saved in the database.
     * @return List of users.
     */
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        log.info("Received request to retrieve all users");
        return userService.getAllUsers();
    }

    /**
     * Fetch a user from the database, by its ID.
     * @param userId ID of the user to fetch.
     * @return The user requested.
     */
    @GetMapping("/by-user-id")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserByID(@RequestParam UUID userId){
        log.info("Received request to retrieve a user by ID");
        return userService.getUserById(userId);
    }

    /**
     * Delete a user from the database, by its ID.
     * @param userId ID of the user to delete.
     * @return The ID of the user that was deleted.
     */
    @DeleteMapping("/by-user-id")
    @ResponseStatus(HttpStatus.OK)
    public UUID deleteUserByID(@RequestParam UUID userId){
        log.info("Received request to delete a user by ID");
        return userService.deleteUserById(userId);
    }

    /**
     * Verify if given user ID exists in the database.
     * @param userId User ID to check.
     * @return A DTO holds a boolean field.
     */
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public UserIdExistsResponse checkUserIdExists(@RequestParam UUID userId){
        log.info("Received request to check if user id '{}' exists", userId);
        return userService.checkUserIdExists(userId);
    }

    /**
     * Verify if given username already exists in the database.
     * @param username Username string to check.
     * @return A DTO holds a boolean field.
     */
    @GetMapping("/username")
    @ResponseStatus(HttpStatus.OK)
    public UsernameExistsResponse checkUsernameExists(@RequestParam String username){
        log.info("Received request to check if username '{}' exists", username);
        return userService.checkUsernameExists(username);
    }

    /**
     * Verify if given email already exists in the database.
     * @param email Email string to check.
     * @return A DTO holds a boolean field.
     */
    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public EmailExistsResponse checkEmailExists(@RequestParam String email){
        log.info("Received request to check if email '{}' exists", email);
        return userService.checkEmailExists(email);
    }

    /**
     * Fetch and retrieve a list of usernames by their ids.
     * @param userIds Given user ids.
     * @return A list of DTO with the user id and the username.
     */
    @GetMapping("/username-list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroupMemberResponse> getUsernames(@RequestParam List<UUID> userIds){
        log.info("Received request to retrieve usernames");
        return userService.getUsernames(userIds);
    }

    @GetMapping("/by-username")
    public UserIdResponse getUserIdByUsername(@RequestParam String username){
        log.info("Received request to retrieve a user's id by username '{}'", username);
        return userService.getUserIdByUsername(username);
    }
}
