package com.MTAPizza.Sympoll.usermanagementservice.validator;

import com.MTAPizza.Sympoll.usermanagementservice.exception.UserNotFoundException;
import com.MTAPizza.Sympoll.usermanagementservice.model.user.User;
import com.MTAPizza.Sympoll.usermanagementservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class Validator {
    private final UserRepository userRepository;
    public void validateNewUser(User user) {
        validatePassword(user.getPassword());
        validateUserName(user.getUsername());
        validateEmail(user.getEmail());
    }

    private void validatePassword(String password) {
        checkValidCharacters(password);
        checkMinimumLength(password);
    }

    private void checkValidCharacters(String password) {
        // Alphanumeric and a set of special characters considered safe
        String validCharactersPattern = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{}|;:'\",.<>?/`~]*$";
        if (!password.matches(validCharactersPattern)) {
            log.warn("Password contains invalid characters.");
            throw new IllegalArgumentException("Password contains invalid characters.");
        }
    }

    private void checkMinimumLength(String password) {
        if (password.length() < 4) {
            log.warn("Password is too short. It must be at least 4 characters long.");
            throw new IllegalArgumentException("Password is too short. It must be at least 4 characters long.");
        }
    }

    private void validateUserName(String username) {
        checkUsernameValidCharacters(username);
        checkUsernameMinimumLength(username);
        checkUsernameDoesntExist(username);
    }

    private void checkUsernameDoesntExist(String username) {
        if(isUsernameExist(username)){
            log.warn("A client tried to create a user with username \"{}\" but this username is already taken.", username);
            throw new IllegalArgumentException(String.format("The username %s already exists.", username));
        }
    }

    private void checkUsernameValidCharacters(String username) {
        // Alphanumeric and underscores are allowed
        String validCharactersPattern = "^[a-zA-Z0-9_]*$";
        if (!username.matches(validCharactersPattern)) {
            log.warn("Username contains invalid characters.");
            throw new IllegalArgumentException("Username contains invalid characters.");
        }
    }

    private void checkUsernameMinimumLength(String username) {
        if (username.length() < 2) {
            log.warn("Username is too short. It must be at least 2 characters long.");
            throw new IllegalArgumentException("Username is too short. It must be at least 2 characters long.");
        }
    }

    private void validateEmail(String email) {
        checkEmailPattern(email);
        checkEmailDoesntExist(email);
    }

    private void checkEmailDoesntExist(String email) {
        if(isEmailExist(email)){
            log.warn("A client tried to create an account with email \"{}\" but this email is already taken.", email);
            throw new IllegalArgumentException(String.format("An account with the email %s already exists.", email));
        }
    }

    private void checkEmailPattern(String email) {
        // Basic email pattern
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailPattern)) {
            log.warn("Invalid email format.");
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    public void checkUsernameExists(String username) {
        if(!isUsernameExist(username)){
            log.warn("A client tried to access user \"{}\" but this username doesn't exist.", username);
            throw new UserNotFoundException(String.format("The username %s does not exist.", username));
        }
    }

    public void checkUserIdExists(UUID userId) {
        if(!isUserIdExists(userId)){
            log.warn("A client tried to access user \"{}\" but this Id doesn't exist.", userId);
            throw new UserNotFoundException(String.format("The id %s does not exist.", userId));
        }
    }

    private boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    private boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isUserIdExists(UUID userID){
        return userRepository.existsById(userID);
    }
}
