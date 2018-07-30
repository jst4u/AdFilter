package com.loit.core.utils.logaop;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AopTestAroundAdvice implements MethodInterceptor {
	protected final Log logger = LogFactory.getLog(getClass());

	public Object invoke(MethodInvocation invocation) throws Exception {
		String fullClassName = invocation.getThis().getClass().getName();
		String className =  StringUtils.substringAfterLast(fullClassName, ".");
		
		String methodName = invocation.getMethod().getName();
		String name = className+":"+methodName;
		
		long showTime =System.currentTimeMillis();
		try {
			Date beginTime = new Date();
			logger.debug("--aop--"+showTime+"--开始方法："+name);
			//	调用目标方法
			Object res = invocation.proceed();

			//	取得方法的参数
//			String str = (String) invocation.getArguments()[0];

			Date endTime = new Date();
			long time = endTime.getTime()-beginTime.getTime();
			logger.debug("--aop--"+showTime+"--结束方法："+name+",用时："+time+"毫秒");

			return res;
		} catch (Throwable e) {
			logger.error("***aop--"+showTime+"--执行拦截执行异常 :"+name);
			throw new Exception("调用around通知时发生错误", e);
		}
	}

}
