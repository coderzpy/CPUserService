package com.example.cpuserservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class UserRole extends BaseModel {

    @Column(unique = true, nullable = false)
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> users;
}
