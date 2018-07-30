package com.loit.core.utils ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YaoJun
 * @version created on Sep 25, 2009 2:13:35 PM
 * 类说明
 */
@SuppressWarnings("unchecked")
public class ObjectUtil {

	/**
	 * <p>把bean转化为map</p>
	 * @param bean
	 * return map
	 */
	public static Map beanToMap(Object entity) {
		Class c = entity.getClass();
		Object fieldValue = null;
		String fieldName = null;
		Field[] fields = c.getDeclaredFields();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		for (Field field : fields) {
			fieldName = field.getName();
			//判断字段的属性是否是public
			if (field.getModifiers() == Modifier.PUBLIC) {
				try {
					fieldValue = field.get(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				fieldValue = invokeGet(entity, fieldName);
			}
			fieldMap.put(fieldName, fieldValue);
		}
		return fieldMap;
	}

	/**
	 * <p>根据字段名，从对象中取该字段的值</p>
	 * @param bean ,fieldName
	 * return obj
	 */
	public static Object invokeGet(Object entity, String fieldName) {
		try {
			String stringLetter = fieldName.substring(0, 1).toUpperCase();
			String getName = "get" + stringLetter + fieldName.substring(1);
			Method method = entity.getClass().getMethod(getName);
			Object obj = method.invoke(entity) ;
			if(null != obj ) {
				return obj ;
			}else {
				return "" ;
			}
		} catch (Exception ex) {

		}
		return null;
	}

	/**
	 * <p>把map转化为bean</p>
	 * @param map
	 * @return obj
	 */
	public static Object mapToBean(Map<String, Field> map, Object obj) {

		Class<?> classType = obj.getClass();
		try {
			for (String key : map.keySet()) {
				java.lang.reflect.Field field = classType.getDeclaredField(key);
				field.setAccessible(true);
				String type = field.getType().getName();
				Object value = null;
				String fieldName = field.getName();
				String stringLetter = fieldName.substring(0, 1).toUpperCase();
				String getName = "get" + stringLetter + fieldName.substring(1);
				System.out.println("getName = " + getName);
				Method method = classType.getMethod(getName);
				System.out.println("method = " + method);
				System.out.println("Obj= " + obj);
				System.out.println("method.invoke(obj)= " + method.invoke(obj));
				String fieldValue = method.invoke(obj).toString();

				if (fieldValue == null || "".equals(fieldValue))
					continue;
				if (type.equalsIgnoreCase("java.lang.Integer")) {
					value = Integer.valueOf(fieldValue);
				} else if (type.equalsIgnoreCase("java.lang.Long")) {
					value = Long.valueOf(fieldValue);
				} else if (type.equalsIgnoreCase("java.lang.Boolean")) {
					value = Boolean.valueOf(fieldValue);
				} else if (type.equalsIgnoreCase("java.lang.Double")) {
					value = Double.valueOf(fieldValue);
				} else if (type.equalsIgnoreCase("java.lang.Float")) {
					value = Float.valueOf(fieldValue);
				} else if (type.equalsIgnoreCase("java.lang.Byte")) {
					value = Byte.valueOf(fieldValue);
				} else if (type.equalsIgnoreCase("java.math.BigDecimal")) {
					value = new BigDecimal(fieldValue);
				} else {
					value = fieldValue;
				}

				System.out.println(key + "=" + value);
				field.set(obj, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static String getFiled(String reqxml) {
		return "";
	}

	

	/**
	 * <p>把bean对象的字段值封装成sql条件</p>
	 * @param obj
	 * return map
	 */
	public static String beanToWhereSql(Object entity) {
		StringBuffer ret = new StringBuffer(" where 1=1");
		Map<String, Object> map = beanToMap(entity);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			String key = entry.getKey();
			if (value != null) {
				ret.append(" and ").append(key).append("=").append(value);
			}
		}
		return ret.toString();
	}
	
	/**
	 * 将List中的数据封装入Map对象放入resutlList对象中
	 * @param datalist
	 * @param resultList
	 * @param fieldStr
	 * @return
	 */
	public static List putInResultList(List datalist , List resultList ,String[] fieldStr) {
		for (Object object : datalist) {
			Class clazz = object.getClass() ;
			Field[] fields = clazz.getDeclaredFields() ;
			Map row = new HashMap();
			for (Field field : fields) {
				Class classType = field.getType() ;
				//当POJO对象中有是Set类型的属性的时候，不显示该字段
				if(!classType.getName().equals("java.util.Set") ){
					String fieldName = field.getName() ;
					Object fieldValue = ObjectUtil.invokeGet(object, fieldName) ;
					for (int i = 0; i < fieldStr.length; i++) {
						if( fieldName.equals(fieldStr[i])) {
							//System.out.println(fieldName + "=" +fieldValue);
							row.put(fieldName, fieldValue);
						}
					}
				}
			}
			resultList.add(row) ;
		}
		return resultList ;
	}
	
	
}
