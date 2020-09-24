package com.spring.camel.integration.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.camel.integration.dto.RequestDto;
import com.spring.camel.integration.dto.ServiceResponse;

@Service
public class RestServiceCaller {

	private static final Logger log = LoggerFactory.getLogger(RestServiceCaller.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	static ServiceResponse cacheSCIntIIIServiceResponse = null;

	@HystrixCommand(fallbackMethod = "callSCIntIFallback")
	public ServiceResponse callSCIntI(RequestDto request) throws Exception {

		log.info("RestServiceCaller callSCIntI() - entrying");
		
		ServiceResponse response = null;
		String REQUEST_URI = "http://eureka-client-first-service/";
		
		ResponseEntity<ServiceResponse> respEntity = new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		try {
			respEntity = restTemplate.postForEntity(REQUEST_URI, request, ServiceResponse.class);
		} catch(Exception ex)	{
			ex.getMessage();
		}
		log.info("RestServiceCaller callSCIntI() - Status: " + respEntity.getStatusCode());
		response = respEntity.getBody();
			
		log.info("RestServiceCaller callSCIntI() - exiting");
		return response;
			
	}

	public ServiceResponse callSCIntIFallback(RequestDto request) throws Exception 	{
		
		ServiceResponse response = new ServiceResponse();
		response.setErrorCode("0");
		response.setErrorMsg("DEFAULT");
		response.setRespCode("0");
		response.setRespMsg("Returning default success response");
		
		log.info("RestServiceCaller callSCIntIFallback() - returning default response");
		return response;
	}
	
	@HystrixCommand(fallbackMethod = "callSCIntIIFallback")
	public ServiceResponse callSCIntII(RequestDto request) throws Exception {

		log.info("RestServiceCaller callSCIntII() - entrying");
		
		ServiceResponse response = null;
		String REQUEST_URI = "http://eureka-client-second-service/";
		
		ResponseEntity<ServiceResponse> respEntity = new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		respEntity = restTemplate.postForEntity(REQUEST_URI, request, ServiceResponse.class);
			
		log.info("RestServiceCaller callSCIntII() - Status: " + respEntity.getStatusCode());
		response = respEntity.getBody();
			
		log.info("RestServiceCaller callSCIntII() - exiting");
		return response;
			
	}
	
	public ServiceResponse callSCIntIIFallback(RequestDto request) throws Exception	{
		
		ServiceResponse response = new ServiceResponse();
		response.setErrorCode("0");
		response.setErrorMsg("TRENDS");
		response.setRespCode("0");
		response.setRespMsg("Watch new trends, this will make your life happy !!");
		
		log.info("RestServiceCaller callSCIntIIFallback() - returning default trending response");
		return response;
	}

	@HystrixCommand(fallbackMethod = "callSCIntIIIFallback")
	public ServiceResponse callSCIntIII(RequestDto request) throws Exception {

		log.info("RestServiceCaller callSCIntIII() - entrying");
		
		ServiceResponse response = null;
		String REQUEST_URI = "http://eureka-client-third-service/";
		
		ResponseEntity<ServiceResponse> respEntity = new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
		respEntity = restTemplate.postForEntity(REQUEST_URI, request, ServiceResponse.class);
			
		log.info("RestServiceCaller callSCIntIII() - Status: " + respEntity.getStatusCode());
		response = respEntity.getBody();
			
		log.info("RestServiceCaller callSCIntIII() - exiting");
		if(respEntity.getStatusCode() == HttpStatus.OK) cacheSCIntIIIServiceResponse = response;
		return response;
			
	}
	
	public ServiceResponse callSCIntIIIFallback(RequestDto request) throws Exception	{
		
		log.info("RestServiceCaller callSCIntIIIFallback() - returning default cache response");
		return null!=cacheSCIntIIIServiceResponse?cacheSCIntIIIServiceResponse:callSCIntIFallback(null);
	}

}