package com.dream.demo.webservice.service.impl;

import com.dream.demo.config.anno.DataSource;
import com.dream.demo.config.datasource.DataSourceType;
import com.dream.demo.mapper.TestMapper;
import com.dream.demo.webservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;


@WebService(
        targetNamespace = "service.webservice.demo.dream.com",
        name = "testPortType",
        serviceName = "testService",
        portName = "testPortName",
        endpointInterface = "com.dream.demo.webservice.service.TestService"
)
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    @DataSource(name = DataSourceType.DP)
    public String test(String name) {
        return testMapper.test();
    }
}
