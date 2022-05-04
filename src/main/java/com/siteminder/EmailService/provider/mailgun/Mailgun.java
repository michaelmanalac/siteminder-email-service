package com.siteminder.EmailService.provider.mailgun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.siteminder.EmailService.controller.dto.request.SendEmailRequest;
import com.siteminder.EmailService.provider.EmailProvider;
import com.siteminder.EmailService.provider.EmailProviderException;

@Component("mailgun")
public class Mailgun extends EmailProvider {
	
	@Value("${email.mailgun.domain}")
	private String DOMAIN;
	@Value("${email.mailgun.api}")
	private String API_KEY;
	private static final String BASE_URL = "https://api.mailgun.net/v3/";	
	private final WebClient webClient = WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(client))
			.baseUrl(BASE_URL)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_RELATED_VALUE)
			.build();
	
	@Override
	public void send(SendEmailRequest request) throws EmailProviderException {
		
		logRequest(request);
		
		try {
			webClient.post()
					.uri(uriBuilder -> uriBuilder
							.path(DOMAIN + "/messages")
							.queryParam("subject", request.getSubject())
							.queryParam("from", request.getFrom())
							.queryParam("to", request.getTo())
							.queryParam("text", request.getMessage())
							.build())
					.headers(httpHeaders -> httpHeaders.setBasicAuth("api", API_KEY))
					.retrieve()
					.bodyToMono(String.class).block();
			
			logSuccess();
			
		} catch(WebClientResponseException e) {
			logError(e);
			next(request);
		}
		
	}

}
