package com.siteminder.EmailService.provider.pepipost.dto.request;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailStruct implements Serializable {

	static final long serialVersionUID = 8594956207065281510L;
	
	String email;
    String name;
    
    public EmailStruct(String email) {
    	this.email = email;
    }

}
