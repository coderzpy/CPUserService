package com.example.cpuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionDto {

    private String message;
    private String errorDetails;
    private LocalDateTime timestamp;
}
