package com.spring.camel.integration.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.camel.integration.constant.SpringCamelConstant;
import com.spring.camel.integration.dto.ResponseDto;
import com.spring.camel.integration.dto.ServiceResponse;

public class PostProcessor implements Processor {

private static final Logger log = LoggerFactory.getLogger(PostProcessor.class);
	
	public void process(Exchange exchange) throws Exception	{
		
		log.info("PostProcessor process() enterying");
		
		ResponseDto resp = new ResponseDto();
		resp.setErrorCode("200");
		resp.setErrorMsg("SUCCESS");
		resp.setRespCode("200");
		
		List<ServiceResponse> respList = new ArrayList<ServiceResponse>();
		ServiceResponse firstServiceResp = exchange.getIn().getHeader(SpringCamelConstant.HEADER_ROUTE_FIRST_RESPONSE, ServiceResponse.class);
		respList.add(firstServiceResp);
		ServiceResponse secondServiceResp = exchange.getIn().getHeader(SpringCamelConstant.HEADER_ROUTE_SECOND_RESPONSE, ServiceResponse.class);
		respList.add(secondServiceResp);
		ServiceResponse thirdServiceResp = exchange.getIn().getHeader(SpringCamelConstant.HEADER_ROUTE_THIRD_RESPONSE, ServiceResponse.class);
		respList.add(thirdServiceResp);
		
		resp.setRespMsg(respList);
		exchange.getIn().setBody(resp);

		log.info("PostProcessor process() removing exchange header");
		exchange.getIn().removeHeaders(SpringCamelConstant.HEADER + "*");
		
		log.info("PostProcessor process() exiting");
	}
}
