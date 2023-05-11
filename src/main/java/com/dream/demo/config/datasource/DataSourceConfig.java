package com.dream.demo.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DataSourceConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.bjxxk")
    public DruidDataSource bjxxkDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return druidDataSource;
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.jsc")
    public DruidDataSource jscDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return druidDataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dp")
    public DruidDataSource dpDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return druidDataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hcp")
    public DruidDataSource hcpDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return druidDataSource;
    }

    //配置 Druid 监控 之  web 监控的 filter
    //WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*,/jdbc/*");
        bean.setInitParameters(initParams);

        //"/*" 表示过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }


    @Bean
    public ServletRegistrationBean servletRegistrationBeanAddress() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        HashMap<String, String> params = new HashMap<>();
        // 设置后台登录账号密码
        params.put("loginUsername", "admin");
        params.put("loginPassword", "admin");
        // 白名单 如params.put("allow","localhost");
        // 为空时表示所有都可访问
        params.put("allow", "");

        bean.setInitParameters(params);
        return bean;
    }

    @Bean("dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier("bjxxkDataSource") DruidDataSource bjxxkDataSource,
                                               @Qualifier("jscDataSource") DruidDataSource jscDataSource,
                                               @Qualifier("dpDataSource") DruidDataSource dpDataSource,
                                               @Qualifier("hcpDataSource") DruidDataSource hcpDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.BJXXK.name(), bjxxkDataSource);
        dataSourceMap.put(DataSourceType.JSC.name(), jscDataSource);
        dataSourceMap.put(DataSourceType.DP.name(), dpDataSource);
        dataSourceMap.put(DataSourceType.HCP.name(), hcpDataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(bjxxkDataSource);
        return dynamicDataSource;
    }

}
