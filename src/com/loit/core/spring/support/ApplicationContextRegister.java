package com.loit.core.spring.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.loit.core.spring.SpringContext;

public class ApplicationContextRegister implements ApplicationContextAware {
	private Log log = LogFactory.getLog(getClass());

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContext.setApplicationContext(applicationContext);
		this.log.info("ApplicationContext registed");
	}
	
}
