package com.spring.camel.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ServiceResponse {

	private String respCode;
	private String respMsg;
	private String errorCode;
	private String errorMsg;
	
}
