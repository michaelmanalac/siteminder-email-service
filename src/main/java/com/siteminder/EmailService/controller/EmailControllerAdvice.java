package com.siteminder.EmailService.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.siteminder.EmailService.controller.dto.response.GenericErrorResponse;

@RestControllerAdvice
public class EmailControllerAdvice  {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({     
        MethodArgumentNotValidException.class,
    })
	public ResponseEntity<GenericErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		
		GenericErrorResponse response = new GenericErrorResponse();
		response.setCode(HttpStatus.BAD_REQUEST.value());
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setErrors(new HashMap<>());
		
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        response.getErrors().put(fieldName, errorMessage);
	    });
	    
	    return ResponseEntity.badRequest().body(response);
	}
	
}
