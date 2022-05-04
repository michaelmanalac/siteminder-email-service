package com.siteminder.EmailService.provider.pepipost.dto.request;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Send implements Serializable {

	private static final long serialVersionUID = 4236741518277078937L;
	
    EmailStruct from;
    String subject;
    List<Content> content;
    List<Personalizations> personalizations;

}
