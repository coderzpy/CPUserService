package com.example.cpuserservice.services;

import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.dtos.UserRegistrationResponseDto;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.cpuserservice.mappers.UserMapper.mapToUser;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserRegistrationResponseDto registerNewUser(UserRegistrationDto userRegistrationDto) {

        try {

            log.info("Attempting to register new user with username: {}", userRegistrationDto.getUsername());

            User user = mapToUser(userRegistrationDto, new User());

            log.info("Successfully registered new user with username: {}", userRegistrationDto.getUsername());

            User userResponse = userRepository.save(user);

            UserRegistrationResponseDto userRegistrationResponseDto = new UserRegistrationResponseDto();
            userRegistrationResponseDto.setUsername(userResponse.getUsername());
            userRegistrationResponseDto.setEmail(userResponse.getEmail());
            userRegistrationResponseDto.setMessage("User registered successfully");

            return userRegistrationResponseDto;

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




}
