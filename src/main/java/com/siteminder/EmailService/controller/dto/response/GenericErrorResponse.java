package com.siteminder.EmailService.controller.dto.response;



import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenericErrorResponse {
	
	int code;
	HttpStatus status;
	Map<String, String> errors;
	
}
