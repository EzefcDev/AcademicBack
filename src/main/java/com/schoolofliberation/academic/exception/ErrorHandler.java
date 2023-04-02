package com.schoolofliberation.academic.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors= new HashMap<String, String>();
        e.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handlerMessageNotReadableException(HttpMessageNotReadableException e){
        return new ResponseEntity<>("El cuerpo no puede estar vacio", HttpStatus.BAD_REQUEST);
    }
}
