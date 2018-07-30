package com.loit.core.utils;

public class SpringBeanUtils {

	public static Class RealClassForCGLIBean(Class clazz){
		
		if(clazz.getSimpleName().indexOf("$$EnhancerByCGLIB$$")>0){
			return clazz.getSuperclass();
		}else{
			return clazz;
		}
	}
}
