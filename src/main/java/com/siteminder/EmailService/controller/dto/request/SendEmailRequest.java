package com.siteminder.EmailService.controller.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendEmailRequest {
	
	@NotBlank
	@Email
	String from;
	
	@NotBlank
	String subject;
	
	Set<@NotBlank @Email String> to;
	
	Set<@Email String> cc;
	
	Set<@Email String> bcc;
	
	String message;
	
}
