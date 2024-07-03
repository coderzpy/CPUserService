package com.example.cpuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profilePictureUrl;
}
