package com.ascertia.userserviceclient.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationExcetion(ApplicationException applicationException){
       return ResponseEntity.ok(applicationException.getMessage());
    }
}
