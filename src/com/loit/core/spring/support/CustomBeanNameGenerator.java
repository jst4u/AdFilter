package com.loit.core.spring.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

public class CustomBeanNameGenerator extends AnnotationBeanNameGenerator {
	private Log log = LogFactory.getLog(getClass());

	public String generateBeanName(BeanDefinition definition,
			BeanDefinitionRegistry registry) {
		String beanNameOld = super.generateBeanName(definition, registry);
		String beanName = null;
		beanName =beanNameOld.endsWith("Impl")?beanName = beanNameOld.substring(0, beanNameOld.length() - 4):beanNameOld;
		log.info("bean:"+beanNameOld+" registe as "+beanName);
		return beanName;
	}
}