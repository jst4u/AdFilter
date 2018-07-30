package com.loit.core.utils.logaop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class AopTestBeforeAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target)
			{
		String str = (String) args[0];
		System.out.println("*这是aop前置的通知的方法 :"+method.getName());

	}

}
