package com.example.cpuserservice.services;

import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerNewUser(UserRegistrationDto userRegistrationDto) {

        try {

            log.info("Attempting to register new user with username: {}", userRegistrationDto.getUsername());
            User user = new User();
            user.setUsername(userRegistrationDto.getUsername());
            user.setEmail(userRegistrationDto.getEmail());

            user.setPasswordHash(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));
            log.info("Successfully registered new user with username: {}", userRegistrationDto.getUsername());

            return userRepository.save(user);
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
