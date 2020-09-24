package com.spring.camel.integration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.camel.integration.constant.SpringCamelConstant;
import com.spring.camel.integration.router.CamelRoutes;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringCamelIntegrationApplicationTest extends CamelTestSupport { 

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		CamelRoutes camelRoutes = new CamelRoutes();
		return camelRoutes;
	}
	
	@Test
	public void contextLoads() throws InterruptedException {
		
		 String output = "Hello, I am preparaing from PreparationforInterview.com";
		 String message = "I have prepared from preparationforinterview.com"; 

		 getMockEndpoint(SpringCamelConstant.ROUTE_PRE_PROCESSOR).expectedMessageCount(1);
			
		 getMockEndpoint(SpringCamelConstant.ROUTE_POST_PROCESSOR).expectedBodiesReceived(message);

		 template.sendBody(SpringCamelConstant.SPRING_CAMEL_ROUTER,output);
		 
		 assertMockEndpointsSatisfied();
		 
	}
}