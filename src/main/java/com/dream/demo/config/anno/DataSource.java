package com.dream.demo.config.anno;

import com.dream.demo.config.datasource.DataSourceType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSource {

    DataSourceType name() default DataSourceType.BJXXK;
}
