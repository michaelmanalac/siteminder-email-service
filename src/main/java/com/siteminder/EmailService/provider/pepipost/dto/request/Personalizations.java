package com.siteminder.EmailService.provider.pepipost.dto.request;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Personalizations implements Serializable {

	private static final long serialVersionUID = 309051868824490855L;
	
	List<EmailStruct> to;
    List<EmailStruct> cc;
    List<EmailStruct> bcc;

}
