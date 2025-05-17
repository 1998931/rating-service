package com.ganesh.user.service.UserService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandle {
@ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundexception(UserNotFound ex){
        String message= ex.getMessage();
        ApiResponse response= ApiResponse.builder().message(message).found(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
