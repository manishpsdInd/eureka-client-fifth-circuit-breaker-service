package com.spring.camel.integration.router;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.spring.camel.integration.constant.SpringCamelConstant;

@Component
public class CamelRoutesRouteBuilder extends RouteBuilder	{

	static Map<String,String> routes = new HashMap<String,String>();

	@Override
	public void configure() throws Exception {
		
		routes.put(SpringCamelConstant.NULL_ROUTE, SpringCamelConstant.ROUTE_PRE_PROCESSOR);
		routes.put(SpringCamelConstant.ROUTE_PRE_PROCESSOR, SpringCamelConstant.ROUTE_FIRST);
		routes.put(SpringCamelConstant.ROUTE_FIRST, SpringCamelConstant.ROUTE_SECOND);
		routes.put(SpringCamelConstant.ROUTE_SECOND, SpringCamelConstant.ROUTE_THIRD);
		routes.put(SpringCamelConstant.ROUTE_THIRD, SpringCamelConstant.ROUTE_POST_PROCESSOR);
		routes.put(SpringCamelConstant.ROUTE_POST_PROCESSOR, SpringCamelConstant.NULL_ROUTE);
	}

	public String getRoute(String body, @Header(Exchange.SLIP_ENDPOINT) String previousRouter) {
		
		if(routes.containsKey(previousRouter))	{
			return routes.get(previousRouter);
		} else {
			return null;
		}
	}

}
