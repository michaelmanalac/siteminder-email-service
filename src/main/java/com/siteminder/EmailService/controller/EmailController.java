package com.siteminder.EmailService.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siteminder.EmailService.controller.dto.request.SendEmailRequest;
import com.siteminder.EmailService.controller.dto.response.GenericResponse;
import com.siteminder.EmailService.provider.EmailProviderException;
import com.siteminder.EmailService.service.EmailService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/v1/send")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailController {
	
	final EmailService emailService;
	final GenericResponse response = new GenericResponse(HttpStatus.OK.value(), HttpStatus.OK, "Email Sent!");
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> send(@Valid @RequestBody SendEmailRequest request) throws EmailProviderException {
		emailService.send(request);
		
		return ResponseEntity.ok().body(response);
	}
}
