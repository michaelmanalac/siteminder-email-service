package com.siteminder.EmailService.service;

import com.siteminder.EmailService.controller.dto.request.SendEmailRequest;
import com.siteminder.EmailService.provider.EmailProviderException;

public interface EmailService {
	
	public void send(SendEmailRequest request) throws EmailProviderException;
	
}
