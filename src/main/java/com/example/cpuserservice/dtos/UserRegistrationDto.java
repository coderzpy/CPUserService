package com.example.cpuserservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    @Size(min = 3, message = "Username must be at least 3 characters")
    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 10, message = "Password must be at least 10 characters")
    @NotBlank(message = "Password is required")
    private String password;
}
