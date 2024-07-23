package com.example.cpuserservice.controllers;

import com.example.cpuserservice.dtos.UserRegistrationResponseDto;
import com.example.cpuserservice.exceptions.CustomExceptionHandler;
import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.models.User;
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

}
