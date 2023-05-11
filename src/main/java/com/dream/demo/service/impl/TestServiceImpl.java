package com.dream.demo.service.impl;

import com.dream.demo.config.anno.DataSource;
import com.dream.demo.config.datasource.DataSourceType;
import com.dream.demo.mapper.TestMapper;
import com.dream.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dream
 * @date 2023/5/10 18:00
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    @DataSource(name = DataSourceType.BJXXK)
    public String test() {
        return testMapper.test();
    }
}
