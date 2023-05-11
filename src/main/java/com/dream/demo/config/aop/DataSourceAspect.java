package com.dream.demo.config.aop;

import com.dream.demo.config.anno.DataSource;
import com.dream.demo.config.datasource.DataSourceType;
import com.dream.demo.config.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DataSourceAspect {

    @Pointcut("@annotation(com.dream.demo.config.anno.DataSource)")
    private void pointCut() {

    }

    @Around("pointCut()")
    private Object changeDataSource(ProceedingJoinPoint point) {
        try {
            Object target = point.getTarget();
            //获取拦截的方法名
            Signature sig = point.getSignature();
            MethodSignature msig = null;
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            msig = (MethodSignature) sig;
            String methodName = msig.getName();
            Method method = target.getClass().getMethod(methodName, msig.getParameterTypes());
            DataSource annotation = method.getAnnotation(DataSource.class);
            DataSourceType name = annotation.name();
            DynamicDataSourceContextHolder.setDataSourceType(name.name());
            Object obj = point.proceed();
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            DynamicDataSourceContextHolder.clearDataSource();
        }

        return null;
    }
}
