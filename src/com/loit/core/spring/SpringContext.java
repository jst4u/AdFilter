package com.loit.core.spring;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

public class SpringContext {
	private static ApplicationContext context;
	private static Log log = LogFactory.getLog(SpringContext.class);

	public static void setApplicationContext(ApplicationContext applicationContext) {
		synchronized (SpringContext.class) {
			log.debug("setApplicationContext, notifyAll");
			context = applicationContext;
			SpringContext.class.notifyAll();
		}
	}

	public static ApplicationContext getApplicationContext() {
		synchronized (SpringContext.class) {
			while (context == null) {
				try {
					log.debug("getApplicationContext, wait...");
					SpringContext.class.wait(120000L);
					if (context == null){
						log.warn("Have been waiting for ApplicationContext to be set for 2 minute",new Exception());
					}
				} catch (InterruptedException a) {
					log.debug("getApplicationContext, wait interrupted");
				}
			}
			return context;
		}
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	public static <T> T getBean(String name, Class<T> type) {
		return (T)getApplicationContext().getBean(name);
	}

	public static Class<?> getType(String name) {
		return getApplicationContext().getType(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
		return (Map<String, T>) getApplicationContext().getBeansOfType(type);
	}

	public static <T> T getBeanOfType(Class<T> type) {
		Map beanMap;
		if ((beanMap = getBeansOfType(type)).size() == 0) {
			throw new NoSuchBeanDefinitionException(
					type,
					"Unsatisfied dependency of type ["
							+ type
							+ "]: expected at least 1 matching bean");
		}
		if (beanMap.size() > 1) {
			throw new NoSuchBeanDefinitionException(type,
					"expected single matching bean but found "
							+ beanMap.size() + ": "
							+ beanMap.keySet());
		}
		return (T) beanMap.values().iterator().next();
	}
}
