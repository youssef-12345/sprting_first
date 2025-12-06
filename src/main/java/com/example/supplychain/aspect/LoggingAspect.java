package com.example.supplychain.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.example.supplychain.*.controller..*(..))")
    public void controllerMethods() {}

    @Pointcut("execution(* com.example.supplychain.*.service..*(..))")
    public void serviceMethods() {}

    @Pointcut("execution(* com.example.supplychain.*.repository..*(..))")
    public void repositoryMethods() {}

    @Before("controllerMethods() || serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "controllerMethods() || serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method: {} executed successfully. Result: {}",
                joinPoint.getSignature().getName(),
                result);
    }

    @AfterThrowing(pointcut = "controllerMethods() || serviceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.error("Exception occurred in method: {}. Exception: {}",
                joinPoint.getSignature().getName(),
                exception.getMessage(), exception);
    }

    @Around("controllerMethods() || serviceMethods()")
    public Object logAroundMethodExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        Object result = proceedingJoinPoint.proceed();
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        logger.debug("Method: {} took {} ms to execute",
                proceedingJoinPoint.getSignature().getName(),
                duration);
        
        return result;
    }
}
