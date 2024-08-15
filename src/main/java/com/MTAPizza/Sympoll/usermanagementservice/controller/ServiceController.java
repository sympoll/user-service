package com.MTAPizza.Sympoll.usermanagementservice.controller;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserCreateRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.id.UserIdExistsRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.id.UserIdExistsResponse;
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

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public UserIdExistsResponse checkUserIdExists(@RequestBody UserIdExistsRequest userIdExistsRequest){
        log.info("Received request to check if user id exists");
        return userService.checkUserIdExists(userIdExistsRequest.userId());
    }

}
