package com.partha.store.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AfterAopAspect {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@AfterReturning(value = "execution(* com.partha.store.services.MvcProductService.*(..))")
	public void afterReturningServiceMethods(JoinPoint joinPoint)
	{
		logger.info("{} Completed", joinPoint);
	}
	
	@After(value = "execution(* com.partha.store.resoures.ProductResource.*(..))")
	public void afterReturningFromGetMethod(JoinPoint joinPoint)
	{
		logger.info("{} execution completed", joinPoint);
	}

}
