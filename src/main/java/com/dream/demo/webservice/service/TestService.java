package com.dream.demo.webservice.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * webservice接口
 */
@WebService(
        targetNamespace = "service.webservice.demo.dream.com",
        name = "testPortType"
)
public interface TestService {


    @WebMethod
    String test(@WebParam(name = "name") String name);
}
