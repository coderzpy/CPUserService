package com.example.cpuserservice.exceptions;

import com.example.cpuserservice.dtos.ExceptionDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends Exception {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setErrorDetails(ex.getCause().getMessage());
        exceptionDto.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setErrorDetails(ex.getRootCause().getMessage());
        exceptionDto.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }

    public String getMessage() {

        return "User already exists";
    }
}
