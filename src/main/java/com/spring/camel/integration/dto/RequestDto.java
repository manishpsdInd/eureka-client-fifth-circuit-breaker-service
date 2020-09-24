package com.spring.camel.integration.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class RequestDto {
	
	private String key;
	private String value;
	
	private @Setter String temp;

}
