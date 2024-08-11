package com.example.cpuserservice.repositories;

import com.example.cpuserservice.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findUserProfileById(Integer id);

}
