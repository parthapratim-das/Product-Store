package com.partha.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AroundAopAspect {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* com.partha.store.dao.*.*(..))")
	public Object daoAroundMethod(ProceedingJoinPoint proceedingJoinPoint)
	{
		System.out.println("Before invoking dao method");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("After invoking dao method method. Return value="+value);
		return value;
	}

}
