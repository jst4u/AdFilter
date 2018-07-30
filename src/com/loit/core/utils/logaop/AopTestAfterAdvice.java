package com.loit.core.utils.logaop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AopTestAfterAdvice implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method, Object[] args,
			Object target)  {
		String str = (String) args[0];
		System.out.println("**这是aop后置的通知的方法 :"+method.getName());

	}

}
