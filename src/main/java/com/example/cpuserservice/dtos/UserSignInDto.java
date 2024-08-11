package com.example.cpuserservice.dtos;

import com.example.cpuserservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInDto {

    String username;
    String password;
}
