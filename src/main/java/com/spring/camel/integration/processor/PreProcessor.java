package com.spring.camel.integration.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.camel.integration.common.CustomException;
import com.spring.camel.integration.dto.RequestDto;

public class PreProcessor implements Processor	{

	private static final Logger log = LoggerFactory.getLogger(PreProcessor.class);
	
	public void process(Exchange exchange) throws Exception	{
				
		log.info("PreProcessor process() enterying");
		
		RequestDto body = exchange.getIn().getBody(RequestDto.class);

		log.info("PreProcessor process(), exchangeID: " + exchange.getExchangeId());
		log.info("PreProcessor process(), validating request body");
		
		if(null == body.getKey() || null == body.getValue())
			throw new CustomException("Initial Validation Failed");
		
		exchange.getIn().setAttachments(exchange.getIn().getAttachments());
		exchange.getIn().setBody(body);
		
		log.info("PreProcessor process() exiting");
	}
}
