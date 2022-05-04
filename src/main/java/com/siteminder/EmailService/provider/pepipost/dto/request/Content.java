package com.siteminder.EmailService.provider.pepipost.dto.request;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Content implements Serializable {
	
	static final long serialVersionUID = 255343322044333663L;
	
	String type;
    String value;
    
    public Content(String value) {
    	this.type = "html";
    	this.value = value;
    }
	
}
