package com.example.cpuserservice.services;

import com.example.cpuserservice.dtos.*;
import com.example.cpuserservice.exceptions.CustomExceptionHandler;
import com.example.cpuserservice.exceptions.UserDoesNotExistException;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.models.UserProfile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    UserRegistrationResponseDto registerNewUser(UserRegistrationDto userRegistrationDto) throws CustomExceptionHandler;
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);

    Optional<User> loginUser(UserSignInDto userSignInDto) throws CustomExceptionHandler, UserDoesNotExistException;

    Optional<UserProfileResponseDto> getUserProfile(Long id);

    UserProfile postUserProfile(UserProfileRequestDto userProfileRequestDto);

}
