package com.example.cpuserservice.controllers;

import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        User newUser = userService.registerNewUser(userRegistrationDto);
        return ResponseEntity.ok(newUser);
    }

}
