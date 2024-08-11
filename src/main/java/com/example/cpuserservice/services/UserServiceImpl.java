package com.example.cpuserservice.services;

import com.example.cpuserservice.dtos.UserProfileResponseDto;
import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.dtos.UserRegistrationResponseDto;
import com.example.cpuserservice.dtos.UserSignInDto;
import com.example.cpuserservice.exceptions.UserDoesNotExistException;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.models.UserProfile;
import com.example.cpuserservice.repositories.UserProfileRepository;
import com.example.cpuserservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mule.runtime.core.api.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.cpuserservice.mappers.UserMapper.*;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserRegistrationResponseDto registerNewUser(UserRegistrationDto userRegistrationDto) {

        try {

            log.info("Attempting to register new user with username: {}", userRegistrationDto.getUsername());

            User user = mapToUser(userRegistrationDto, new User());

            log.info("Successfully registered new user with username: {}", userRegistrationDto.getUsername());

            User userResponse = userRepository.save(user);

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

        try {
            Optional<User> user = userRepository.findByUsername(userSignInDto.getUsername());

            if(user.isPresent()) {

                if (verifyPassword(userSignInDto.getPassword(), user.get().getPasswordHash())) {
                    return user;
                } else {
                    log.error("Error logging in user with username: {}", userSignInDto.getUsername());
                    throw new UserDoesNotExistException("Username or password does not exist");
                }

            }else {
                log.error("Error logging in user with username: {}", userSignInDto.getUsername());
                throw new UserDoesNotExistException("Username or password does not exist");
            }
        }catch (Exception e) {
            log.error("Error logging in user with username: {}", userSignInDto.getUsername(), e);
            throw new UserDoesNotExistException("Username or password does not exist", e);
        }
    }

    public Optional<UserProfileResponseDto> getUserProfile(Integer id) {

        try {

            UserProfile userProfile = userProfileRepository.findUserProfileById(id);

            UserProfileResponseDto userProfileResponse = mapToUserProfileDto(userProfile);
            return Optional.of(userProfileResponse);


        } catch (Exception e) {

            throw new UserDoesNotExistException("Username does not exist", e);
        }
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }


}
