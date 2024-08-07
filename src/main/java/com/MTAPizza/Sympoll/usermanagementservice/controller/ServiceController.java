package com.MTAPizza.Sympoll.usermanagementservice.controller;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserCreateRequest;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.MTAPizza.Sympoll.usermanagementservice.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

}
