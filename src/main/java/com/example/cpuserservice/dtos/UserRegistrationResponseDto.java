package com.example.cpuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationResponseDto {

    String message;
    String username;
    String email;
}
