package com.example.cpuserservice.repositories;

import com.example.cpuserservice.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
