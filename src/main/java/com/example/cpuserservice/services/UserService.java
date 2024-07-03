package com.example.cpuserservice.services;

import com.example.cpuserservice.dtos.UserRegistrationDto;
import com.example.cpuserservice.models.User;
import com.example.cpuserservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public interface UserService {

    User registerNewUser(UserRegistrationDto userRegistrationDto);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);


}
