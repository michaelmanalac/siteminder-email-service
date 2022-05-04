package com.siteminder.EmailService.service;


import org.springframework.stereotype.Service;

import com.siteminder.EmailService.controller.dto.request.SendEmailRequest;
import com.siteminder.EmailService.provider.EmailProvider;
import com.siteminder.EmailService.provider.EmailProviderException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailServiceImpl implements EmailService {
	
	private final EmailProvider mailgun;
	private final EmailProvider pepipost;
	
	@Override
	public void send(SendEmailRequest request) throws EmailProviderException {
		
		mailgun.setNextProvider(pepipost)
				.setNextProvider(null);
		
		mailgun.send(request);
	}

}
