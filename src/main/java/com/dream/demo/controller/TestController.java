package com.dream.demo.controller;

import com.dream.demo.service.TestService;
import com.dream.demo.utils.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dream
 * @date 2023/5/6 15:17
 */

@RestController
@RequestMapping("/test/datasource")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping()
    public RespResult test() {
        String test = testService.test();
        return RespResult.success(test);
    }

}
