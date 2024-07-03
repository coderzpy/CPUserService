package com.example.cpuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    private String username;
    private String email;
    private String password;
}
