package com.example.cpuserservice.mappers;

import com.example.cpuserservice.dtos.UserProfileResponseDto;
import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.dtos.UserRegistrationResponseDto;
import com.example.cpuserservice.dtos.UserSignInDto;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.models.UserProfile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserMapper {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private static UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
    private static UserRegistrationResponseDto userRegistrationResponseDto = new UserRegistrationResponseDto();

    private static UserSignInDto userSignInDto = new UserSignInDto();

    public static UserRegistrationDto mapToUserRegistrationDto(User user) {
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

    public static UserRegistrationResponseDto mapToUserRegistrationResponseDto(User user) {
        userRegistrationResponseDto.setEmail(user.getEmail());
        userRegistrationResponseDto.setUsername(user.getUsername());
        userRegistrationResponseDto.setMessage("User registered successfully");
        return userRegistrationResponseDto;
    }

    public static User mapToUserSignInDto(User user) {
        user.setUsername(userSignInDto.getUsername());
        user.setPasswordHash(bCryptPasswordEncoder.encode(userSignInDto.getPassword()));
        return user;
    }

    public static UserProfileResponseDto mapToUserProfileDto(UserProfile userProfile) {
        UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto();
        userProfileResponseDto.setFirstName(userProfile.getFirstName());
        userProfileResponseDto.setLastName(userProfile.getLastName());
        userProfileResponseDto.setProfilePictureUrl(userProfile.getProfielPictureUrl());
        userProfileResponseDto.setPhoneNumber(String.valueOf(userProfile.getPhoneNumber()));

        return userProfileResponseDto;
    }
}
