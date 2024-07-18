package com.example.cpuserservice.mappers;

import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    private  static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static UserRegistrationDto mapToUserRegistrationDto(User user, UserRegistrationDto userRegistrationDto) {
        userRegistrationDto.setEmail(user.getEmail());
        userRegistrationDto.setUsername(user.getUsername());
        userRegistrationDto.setPassword(user.getPasswordHash());
        return userRegistrationDto;
    }

    public static User mapToUser(UserRegistrationDto userRegistrationDto, User user) {
        user.setEmail(userRegistrationDto.getEmail());
        user.setUsername(userRegistrationDto.getUsername());
        user.setPasswordHash(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));
        return user;
    }
}
