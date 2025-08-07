package com.trend_kart.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    // @Before("execution(* com.trend_kart..*(..))")
    // @Before("execution(* com.trend_kart..service..*(..))")
    public void preCallLog(JoinPoint joinPoint) {
        log.info("LoggingAspect::preCallLog::signature:{}", joinPoint.getSignature().getName());
    }

    @Around("execution(* com.trend_kart..service..*(..))")
    public void aroundCallLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("LoggingAspect::aroundCallLog::entering::signature:{}::", proceedingJoinPoint.getSignature().getName());
        proceedingJoinPoint.proceed();
        log.info("LoggingAspect::aroundCallLog::exiting::signature:{}::", proceedingJoinPoint.getSignature().getName());
    }
}
