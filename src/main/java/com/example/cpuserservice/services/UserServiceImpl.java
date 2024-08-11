package com.example.cpuserservice.services;

import com.example.cpuserservice.dtos.*;
import com.example.cpuserservice.exceptions.UserDoesNotExistException;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.models.UserProfile;
import com.example.cpuserservice.repositories.UserProfileRepository;
import com.example.cpuserservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.mule.runtime.core.api.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.cpuserservice.mappers.UserMapper.*;
import static com.example.cpuserservice.mappers.UserProfileMapper.*;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserRegistrationResponseDto registerNewUser(UserRegistrationDto userRegistrationDto) {

        log.info("Attempting to register new user with username: {}", userRegistrationDto.getUsername());

        User user = mapToUser(userRegistrationDto, new User());

        try {

            User userResponse = userRepository.save(user);

            log.info("Successfully registered new user with username: {}", userRegistrationDto.getUsername());

            return mapToUserRegistrationResponseDto(userResponse);

        } catch (DataIntegrityViolationException e) {

            log.error("Error registering new user with username: {}", userRegistrationDto.getUsername(), e);
            throw new DataIntegrityViolationException("Username or email already exists", e);
        }

    }


    public Optional<User> findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }
    public Optional<User> findUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> loginUser(UserSignInDto userSignInDto) {

        User user = userRepository.findByUsername(userSignInDto.getUsername())
                .orElseThrow(() -> {
                    log.error("Error logging in user with username: {}", userSignInDto.getUsername());
                    return new UserDoesNotExistException("Username or password does not exist");
                });

        if (verifyPassword(userSignInDto.getPassword(), user.getPasswordHash())) {
            return Optional.of(user);
        } else {
            log.error("Error logging in user with username: {}", userSignInDto.getUsername());
            throw new UserDoesNotExistException("Username or password does not exist");
        }

    }

    public Optional<UserProfileResponseDto> getUserProfile(Long id) {



            UserProfile userProfile = userProfileRepository.findById(id)
                    .orElseThrow(() -> new UserDoesNotExistException(("User not found")));

            UserProfileResponseDto userProfileResponse = mapToProfileResponseDto(userProfile);
            return Optional.of(userProfileResponse);

    }

    public UserProfile postUserProfile(UserProfileRequestDto dto) {

        User user = userRepository.findByUsername(dto.getUserSignInDto().getUsername())
                .orElseThrow(() -> new UserDoesNotExistException("User not found"));

        UserProfile userProfile = mapToUserProfile(dto, new User());

        return userProfileRepository.save(userProfile);

    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }


}
