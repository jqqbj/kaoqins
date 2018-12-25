package com.rrx.kaoqins.core.log;

import com.rrx.kaoqins.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author JQQ
 * 以日志为例，演示Spring AOP实现
 */
@Slf4j
@Aspect
@Component
@Order(0)
public class LogAspect {

    @Autowired
    LogService logService;

    @Pointcut("@annotation(com.rrx.kaoqins.core.log.Log)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Log log2 = signature.getMethod().getAnnotation(Log.class);
        //LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        //String[] parameterNames = discoverer.getParameterNames(signature.getMethod());
        logService.saveLog(log2);
        try {
            return pjp.proceed();
        } catch (BusinessException e) {
            log.error("方法执行失败", e);
            throw e;
        } catch (Throwable throwable) {
            log.error("方法执行失败", throwable);
            throw new BusinessException(throwable);
        }
    }

}
