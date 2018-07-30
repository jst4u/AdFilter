package com.loit.core.spring.support;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import com.loit.core.utils.ReflectionUtil;



public class CustomBeanWrapper extends BeanWrapperImpl {
	public CustomBeanWrapper(Object object) {
		super(object);
		registerCustomEditor(Date.class, new CustomDateEditor());
		registerCustomEditor(Array.newInstance(String.class, 0).getClass(), new CustomStringArrayEditor());
	}

	public CustomBeanWrapper(Class<?> clazz) {
		super(clazz);
		registerCustomEditor(Date.class, new CustomDateEditor());
		registerCustomEditor(Array.newInstance(String.class, 0).getClass(), new CustomStringArrayEditor());
	}

	public PropertyDescriptor[] getPropertyDescriptorsInDeclaringOrder() {
		PropertyDescriptor[] propertyDescriptors = super.getPropertyDescriptors();
		List<Method> methodList = ReflectionUtil.getMethodsInDeclaringOrder(getWrappedClass());
//		Map<Method,PropertyDescriptor> proDescMap = new HashMap<Method,PropertyDescriptor>();
		
		Set<PropertyDescriptor> propertyDescriptorInOrder = new LinkedHashSet<PropertyDescriptor>();
		for (Iterator<Method> localIterator = methodList.iterator(); localIterator.hasNext();) {
			
			Method method = (Method) localIterator.next();
			
			for(int i=0;i<propertyDescriptors.length;i++){
				PropertyDescriptor pd = propertyDescriptors[i];
				if(method.equals(propertyDescriptors[i].getReadMethod())||method.equals(propertyDescriptors[i].getWriteMethod())){
					propertyDescriptorInOrder.add(pd);
					continue;
				}
			}
		}

		return (PropertyDescriptor[]) propertyDescriptorInOrder.toArray(new PropertyDescriptor[propertyDescriptorInOrder.size()]);
	}

	public void copyPropertiesTo(Object destinationBean, List<String> propertyNames) {
		CustomBeanWrapper bean = new CustomBeanWrapper(destinationBean);
		for (Iterator<String> localIterator = propertyNames.iterator(); localIterator.hasNext();) {
			String propertyName = (String) localIterator.next();
			bean.setPropertyValue(propertyName, getPropertyValue(propertyName));
		}
	}

	public Object getPropertyValueRecursively(String propertyName) throws BeansException {
		int b;
		if ((b = propertyName.indexOf(".")) == -1)
			return getPropertyValue(propertyName);
		Object a;
		if ((a = getPropertyValue(propertyName.substring(0, b))) == null) {
			return null;
		}
		return new CustomBeanWrapper(a).getPropertyValueRecursively(propertyName.substring(b + 1));
	}

	public Class<?> getPropertyTypeRecursively(String propertyName) throws BeansException {
		int b;
		if ((b = propertyName.indexOf(".")) == -1)
			return getPropertyType(propertyName);
		Object a;
		if ((a = getPropertyValue(propertyName.substring(0, b))) == null) {
			return null;
		}
		return new CustomBeanWrapper(a).getPropertyTypeRecursively(propertyName.substring(b + 1));
	}
	
	public static void main(String[] args){
//		CustomBeanWrapper cb = new CustomBeanWrapper(new NewTableModel());
//		PropertyDescriptor[] pd =  cb.getPropertyDescriptorsInDeclaringOrder();
//		System.out.println(pd.length);
//		for(int i=0;i<pd.length;i++){
//			System.out.println(pd[i]);
//			if(null != pd[i]){
//				System.out.println(pd[i].getDisplayName());
//			}
//		}
//		
//		 pd =  cb.getPropertyDescriptors();
//		System.out.println(pd.length);
//		for(int i=0;i<pd.length;i++){
//			System.out.println(pd[i]);
//			if(null != pd[i]){
//				System.out.println(pd[i].getDisplayName());
//			}
//		}
	}
	
	
}
