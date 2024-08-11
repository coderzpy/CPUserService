package com.example.cpuserservice.exceptions;

import com.example.cpuserservice.dtos.ExceptionDto;
import org.mule.runtime.core.api.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, ExceptionDto>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, ExceptionDto> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setMessage(error.getDefaultMessage());
            exceptionDto.setErrorDetails(error.getField());
            exceptionDto.setTimestamp(LocalDateTime.now());
            errors.put(error.getField(), exceptionDto);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ExceptionDto> handlerUserDoesNotExistException(UserDoesNotExistException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setErrorDetails(ex.getCause().getMessage());
        exceptionDto.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    public String getMessage() {

        return "User already exists";
    }
}
