package com.example.cpuserservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserProfile extends BaseModel {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String address;
    private LocalDateTime dateOfBirth;
    private String profielPictureUrl;

    private DateFormat createdAt;
    private DateFormat updatedAt;

}
