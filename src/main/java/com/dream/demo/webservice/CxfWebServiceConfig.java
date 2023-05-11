package com.dream.demo.webservice;

import com.dream.demo.webservice.service.TestService;
import com.dream.demo.webservice.service.impl.TestServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfWebServiceConfig {

    @Bean("cxfServletRegistration")
    public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/test/services/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public TestService testService() {
        return new TestServiceImpl();
    }

    @Bean
    public Endpoint endpoint(TestService testService) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), testService);
        endpoint.publish("/service");
        return endpoint;
    }
}
