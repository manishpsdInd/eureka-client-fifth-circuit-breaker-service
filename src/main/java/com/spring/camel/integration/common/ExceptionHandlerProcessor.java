package com.spring.camel.integration.common;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.camel.integration.constant.SpringCamelConstant;
import com.spring.camel.integration.dto.ResponseDto;
import com.spring.camel.integration.dto.ServiceResponse;

public class ExceptionHandlerProcessor implements Processor	{
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerProcessor.class);
	
	public void process(Exchange exchange) throws Exception	{

		log.info("ExceptionHandlerProcessor process() enterying");
		
		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		String msg = SpringCamelConstant.BLANK_STRING;
		try {
			CustomException custExce = (CustomException) exception;
			msg = custExce.getErrMsg();
		} catch(Exception ex)	{
			msg = exception.getMessage();
		}
		
		log.error(exception.getMessage());
		
		ResponseDto resp = new ResponseDto();
		resp.setErrorCode("400");
		resp.setErrorMsg("FAILURE");
		resp.setRespCode("400");
		ServiceResponse dummyResp = new ServiceResponse();
		dummyResp.setRespMsg(msg);
		
		exchange.getIn().setBody(resp);

		log.info("PostProcessor process() removing exchange header");
		exchange.getIn().removeHeaders(SpringCamelConstant.HEADER + "*");
		
		log.info("ExceptionHandlerProcessor process() exiting");

	}
}
