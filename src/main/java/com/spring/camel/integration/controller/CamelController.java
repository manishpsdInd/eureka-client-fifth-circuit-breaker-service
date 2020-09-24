package com.spring.camel.integration.controller;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.camel.integration.constant.SpringCamelConstant;
import com.spring.camel.integration.dto.RequestDto;
import com.spring.camel.integration.dto.ResponseDto;

@RestController
@EnableEurekaClient
public class CamelController {
	
	private static final Logger log = LoggerFactory.getLogger(CamelController.class);
	
	@Autowired
	CamelContext camelContext;
	
	@Autowired
	ProducerTemplate producerTemplate;
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/JSON", "application/XML"}, produces = "application/JSON")
	public @ResponseBody ResponseDto startCamel(@RequestBody RequestDto request) {
		
		ResponseDto response = (ResponseDto) producerTemplate.sendBody(SpringCamelConstant.SPRING_CAMEL_ROUTER, ExchangePattern.InOut, request);

		log.info("CamelController End - " + response);
		return response;
	}
}