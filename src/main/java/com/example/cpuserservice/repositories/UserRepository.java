package com.example.cpuserservice.repositories;
import com.example.cpuserservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email) ;
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPasswordHash(String username, String password);
}
