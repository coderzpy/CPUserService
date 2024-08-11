package com.example.cpuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequestDto {

    private  UserSignInDto userSignInDto;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    public String address;
    private String profilePictureUrl;
}
