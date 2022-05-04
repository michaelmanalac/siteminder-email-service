package com.siteminder.EmailService.provider;

import java.time.Duration;

import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.siteminder.EmailService.controller.dto.request.SendEmailRequest;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.HttpClient;

@Slf4j
public abstract class EmailProvider {
	
	protected static final HttpClient client = HttpClient.create().responseTimeout(Duration.ofSeconds(5)); 
	private EmailProvider nextProvider;
	
	public EmailProvider setNextProvider(EmailProvider nextProvider) {
		this.nextProvider = nextProvider;
		return nextProvider;
	}
	
	public abstract void send(SendEmailRequest request) throws EmailProviderException;
	
	protected void next(SendEmailRequest request) {
		if (nextProvider == null) {
			log.info("====================================================");
			throw new EmailProviderException("All email providers failed to send the mail");
		}
		
		log.info("====================================================");
		log.info("Retrying using next available provider...");
		nextProvider.send(request);
	}
	
	protected void logRequest(SendEmailRequest request) {
		log.info("====================================================");
		log.info("Sending email using "+ this.getClass().getSimpleName() +"....");
		log.info("TO:\t" + request.getTo());
		log.info("CC:\t" + request.getCc());
		log.info("BCC\t" + request.getBcc());
		log.info("FROM:\t" + request.getFrom());
		log.info("SUBJECT:\t" + request.getSubject());
		log.info("MESSAGE:\t" + request.getMessage());
	}
	
	protected void logSuccess() {
		log.info("====================================================");
		log.info("Email Sent.");
	}
	
	protected void logError(WebClientResponseException exception) {
		log.info("====================================================");
		log.error("Error occured the email provider " + this.getClass().getSimpleName());
		log.error("Status:\t" + exception.getRawStatusCode());
		log.error("Response:\t" + exception.getResponseBodyAsString());
	}
	
}
