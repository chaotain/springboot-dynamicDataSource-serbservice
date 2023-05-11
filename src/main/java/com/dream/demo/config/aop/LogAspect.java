package com.dream.demo.config.aop;

import com.dream.demo.config.anno.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
public class LogAspect {

    @Autowired
    ConcurrentHashMap countMap;

    @Pointcut("@annotation(com.dream.demo.config.anno.Log)")
    private void pointCut() {

    }

    @Before("pointCut()")
    private void doBefore(JoinPoint point) {
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
            Log annotation = method.getAnnotation(Log.class);
            StringBuffer sb = new StringBuffer(msig.getDeclaringTypeName());
            sb.append(".")
                    .append(msig.getName())
                    .append("(action:")
                    .append(annotation.action())
                    .append(",describe:")
                    .append(annotation.describe())
                    .append(")");
            countMap.put(sb.toString(),((int)countMap.getOrDefault(sb.toString(),0) + 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
