package com.example.cpuserservice.services;

import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User registerNewUser(UserRegistrationDto userRegistrationDto) {

        try {

            User user = new User();
            user.setUsername(userRegistrationDto.getUsername());
            user.setEmail(userRegistrationDto.getEmail());

            user.setPasswordHash(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));

            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
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
