package com.spring.camel.integration.router;

import org.springframework.stereotype.Component;

import com.spring.camel.integration.common.ExceptionHandlerProcessor;
import com.spring.camel.integration.constant.SpringCamelConstant;
import com.spring.camel.integration.processor.FirstServiceCallProcessor;
import com.spring.camel.integration.processor.ThirdServiceCallProcessor;
import com.spring.camel.integration.processor.PostProcessor;
import com.spring.camel.integration.processor.PreProcessor;
import com.spring.camel.integration.processor.SecondServiceCallProcessor;

@Component
public class CamelRoutes extends CamelRoutesRouteBuilder {

	public void configure() throws Exception {
		
		onException(Exception.class)
			.handled(true).maximumRedeliveries(0).to(SpringCamelConstant.EXCEPTION);
		//onException(ValidationException.class).to("activemq:validationFailed");

		from(SpringCamelConstant.SPRING_CAMEL_ROUTER)
			.dynamicRouter(method(CamelRoutesRouteBuilder.class, "getRoute"));
		
		from(SpringCamelConstant.ROUTE_PRE_PROCESSOR)
			.bean(PreProcessor.class);
		
		from(SpringCamelConstant.ROUTE_FIRST)
			.bean(FirstServiceCallProcessor.class);
		
		from(SpringCamelConstant.ROUTE_SECOND)
			.bean(SecondServiceCallProcessor.class);
		
		from(SpringCamelConstant.ROUTE_THIRD)
			.bean(ThirdServiceCallProcessor.class);

		from(SpringCamelConstant.ROUTE_POST_PROCESSOR)
			.bean(PostProcessor.class);
		
		from(SpringCamelConstant.EXCEPTION)
			.bean(ExceptionHandlerProcessor.class);
		
		//from("jetty:http://localhost:8080/foo").to("cxf:bean:addAccountDetails")
		//from("netty:tcp://localhost:61618").bean(TcpProcessor.class);
		
	}
}