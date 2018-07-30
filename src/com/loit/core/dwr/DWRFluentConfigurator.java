package com.loit.core.dwr;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import com.loit.core.spring.CommonManager;
import com.loit.core.spring.SpringContext;

public class DWRFluentConfigurator extends FluentConfigurator {

	public void configure() {
		Map<String, CommonManager> managers = SpringContext.getBeansOfType(CommonManager.class);
		Iterator<String> managerIterator = managers.keySet().iterator();
		while (managerIterator.hasNext()) {
			String managerName = managerIterator.next();
			CommonManager manager = managers.get(managerName);
			FluentConfigurator config = withCreator("spring").addParam("beanName", managerName).addParam("javascript", managerName);
			Method[] methods = manager.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				String methodName = method.getName();
				config.include(methodName);
			}
		}
	}
}
