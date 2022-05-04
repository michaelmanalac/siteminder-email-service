package com.siteminder.EmailService.provider.pepipost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.siteminder.EmailService.provider.pepipost.dto.request.Content;
import com.siteminder.EmailService.provider.pepipost.dto.request.EmailStruct;
import com.siteminder.EmailService.provider.pepipost.dto.request.Personalizations;
import com.siteminder.EmailService.provider.pepipost.dto.request.Send;

import reactor.core.publisher.Mono;

@Component("pepipost")
public class Pepipost extends EmailProvider {
	
	@Value("${email.pepipost.api}")
	private String API_KEY;
	private Send send = new Send();
	private static final String BASE_URL = "https://emailapi.netcorecloud.net/v5";
	private final WebClient webClient = WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(client))
			.baseUrl(BASE_URL)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();

	@Override
	public void send(SendEmailRequest request) throws EmailProviderException {
		
		logRequest(request);
		buildRequest(request);
		
		try {
			webClient.post()
					.uri("/mail/send")
					.body(Mono.just(send), Send.class)
					.header("api_key", API_KEY)
					.retrieve()
					.bodyToMono(String.class).block();
			
			logSuccess();
			
		} catch(WebClientResponseException e) {
			logError(e);
			next(request);
		}
	}
	
	private void buildRequest(SendEmailRequest request) {
		
		send.setFrom(new EmailStruct(request.getFrom()));
		send.setSubject(request.getSubject());
		
		
		Content content = new Content(request.getMessage());
		send.setContent(List.of(content));
		
		
		Personalizations personalizations = new Personalizations();
		
		Optional.ofNullable(request.getTo()).ifPresent(to -> {
				
				personalizations.setTo(request.getTo().stream()
							.map(EmailStruct::new)
							.collect(Collectors.toCollection(ArrayList::new)));
			
		});
		
		Optional.ofNullable(request.getTo()).ifPresent(to -> {
			
			personalizations.setCc(request.getCc().stream()
						.map(EmailStruct::new)
						.collect(Collectors.toCollection(ArrayList::new)));
		
		});
		
		Optional.ofNullable(request.getTo()).ifPresent(to -> {
			
			personalizations.setBcc(request.getBcc().stream()
						.map(EmailStruct::new)
						.collect(Collectors.toCollection(ArrayList::new)));
		
		});
		
		send.setPersonalizations(List.of(personalizations));
		
	}

}
