package com.spring.camel.integration.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ResponseDto {

	private String respCode;
	private List<ServiceResponse> respMsg;
	private String errorCode;
	private String errorMsg;
	
}
