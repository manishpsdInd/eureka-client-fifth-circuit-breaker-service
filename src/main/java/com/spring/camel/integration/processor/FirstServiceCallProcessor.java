package com.spring.camel.integration.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.camel.integration.common.RestServiceCaller;
import com.spring.camel.integration.constant.SpringCamelConstant;
import com.spring.camel.integration.dto.RequestDto;
import com.spring.camel.integration.dto.ResponseDto;
import com.spring.camel.integration.dto.ServiceResponse;

public class FirstServiceCallProcessor implements Processor {

	private static final Logger log = LoggerFactory.getLogger(FirstServiceCallProcessor.class);

	@Autowired
	RestServiceCaller restServiceCaller;

	public void process(Exchange exchange) throws Exception {

		log.info("FirstServiceCallProcessor process() enterying");

		RequestDto body = exchange.getIn().getBody(RequestDto.class);

		RequestDto serviceRequest = new RequestDto();
		serviceRequest.setKey(body.getKey());
		serviceRequest.setValue(body.getKey() + "-" + body.getValue());

		ServiceResponse serviceResponse = restServiceCaller.callSCIntI(serviceRequest);

		exchange.getIn().setHeader(SpringCamelConstant.HEADER_ROUTE_FIRST_RESPONSE, serviceResponse);
		exchange.getIn().setBody(serviceResponse, ResponseDto.class);

		log.info("FirstServiceCallProcessor process() exiting");

	}
}