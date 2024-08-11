package com.example.cpuserservice.controllers;

import com.example.cpuserservice.dtos.*;
import com.example.cpuserservice.exceptions.CustomExceptionHandler;
import com.example.cpuserservice.exceptions.UserDoesNotExistException;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.models.UserProfile;
import com.example.cpuserservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) throws CustomExceptionHandler {

        UserRegistrationResponseDto newUser = userService.registerNewUser(userRegistrationDto);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/login")
    public ResponseEntity<User> getUserByUsernameAndPassword(@RequestBody UserSignInDto userSignInDto) throws CustomExceptionHandler, UserDoesNotExistException {
        return userService.loginUser(userSignInDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileResponseDto> getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/profile")
    public ResponseEntity<UserProfile> postUserProfile(@RequestBody UserProfileRequestDto userProfileRequestDto) {

        UserProfile userProfile = userService.postUserProfile(userProfileRequestDto);

        return ResponseEntity.ok(userProfile);
    }
}
