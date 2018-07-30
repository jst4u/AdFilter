package com.loit.core.utils.logaop;

import org.springframework.aop.ThrowsAdvice;

public class AopTestExceptionAdvice implements ThrowsAdvice {
	
	public void afterThrowing(Exception sx) {
		System.out.println("****这是aop抛出异常的通知的方法 :"+sx.getMessage());

	}

}
