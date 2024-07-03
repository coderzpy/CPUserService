package com.example.cpuserservice.repositories;

import com.example.cpuserservice.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {


}
