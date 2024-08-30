package com.kgisl.sb10.config;
import java.util.Arrays;
 
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
@Aspect
@Component
public class LoggingAspect {
 
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
 
    @Before(value = "execution(* com.kgisl.sb10.service.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Before method:" + joinPoint.getSignature());
        LOGGER.info("Before method:" + joinPoint.getSignature());
        LOGGER.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("Entering in Method :  " + joinPoint.getSignature().getName());
        LOGGER.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
    }
 
    @After(value = "execution(* com.kgisl.sb101.service.PersonService.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("After method:" + joinPoint.getSignature());
        LOGGER.info("After method:" + joinPoint.getSignature());
        LOGGER.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("Entering in Method :  " + joinPoint.getSignature().getName());
        LOGGER.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
    }
 
    @AfterThrowing(pointcut = "execution(* com.kgisl.sb101.controller.*.*(..))", throwing = "excep")
    public void afterThrowing(JoinPoint joinPoint, Throwable excep) {
        System.out.println("After Throwing Method:" + joinPoint.getSignature());
        LOGGER.info("After Throwing Method:" + joinPoint.getSignature());
        LOGGER.error("Exception in method: " + joinPoint.getSignature().getName() + " with arguments: "
                + Arrays.toString(joinPoint.getArgs()));
        LOGGER.error("Exception: ", excep);
    }
}