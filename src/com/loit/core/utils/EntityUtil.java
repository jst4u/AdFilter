package com.loit.core.utils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.apache.commons.beanutils.BeanUtils;

import com.loit.core.commom.query.BaseQueryCondition;
import com.loit.core.commom.query.BaseQueryItem;
import com.loit.core.hibernate.model.BaseModel;
import com.loit.core.spring.support.CustomBeanWrapper;

public class EntityUtil {
	public static Class<? extends BaseModel> getEntityClass(String entityName) throws Exception {
		String c = entityName + "Model";
		List b;
		for (Class a : ClassUtil.getModelClasses()) {
			if (a.getSimpleName().equals(c)) {
				return a;
			}
		}
		return null;
	}

	public static Class<? extends BaseModel> getEntityClass(Class<? extends BaseModel> entityClass) {

		Class a = entityClass;
		do
			if ((a = a.getSuperclass()) == null)
				break;
		while (!a.isAnnotationPresent(Entity.class));

		if (a == null) {
			return entityClass;
		}
		return a;
	}

	public static <TYPE extends BaseModel> TYPE convertEntityType(Object entity, Class<TYPE> type) {
		if (type.isAssignableFrom(entity.getClass()))
			return (TYPE) entity;
		try {
			TYPE typeModel = type.newInstance();
			ClassUtil.copyProperties(entity, typeModel);
			return typeModel;
		} catch (Exception a) {
			throw new RuntimeException(a);
		}

	}

	public static Serializable getId(BaseModel entity) {
		if (entity.getClass().isAnnotationPresent(IdClass.class)) {
			Class d = ((IdClass) entity.getClass().getAnnotation(IdClass.class)).value();
			try {
				Serializable b = (Serializable) d.newInstance();
				BeanUtils.copyProperties(entity, b);
				return b;
			} catch (Exception a) {
				throw new RuntimeException(a);
			}
		}
		String c;
		if ((c = getIdFieldName(entity.getClass())) != null) {
			return (Serializable) new CustomBeanWrapper(entity).getPropertyValue(c);
		}
		return null;
	}

	public static List<String> getFieldNames(Class<? extends BaseModel> entityClass) {
		List c = new ArrayList();
		CustomBeanWrapper b;
		PropertyDescriptor[] arrayOfPropertyDescriptor;
		int j = (arrayOfPropertyDescriptor = (b = new CustomBeanWrapper(entityClass)).getPropertyDescriptorsInDeclaringOrder()).length;
		for (int i = 0; i < j; i++) {
			PropertyDescriptor a = arrayOfPropertyDescriptor[i];
			if ((!b.isReadableProperty(a.getName())) || (!a.getReadMethod().isAnnotationPresent(Column.class)))
				continue;
			c.add(a.getName());
		}

		return c;
	}

	public static String getIdFieldName(Class<? extends BaseModel> entityClass) {
		Method[] arrayOfMethod;
		int j = (arrayOfMethod = entityClass.getMethods()).length;
		for (int i = 0; i < j; i++) {
			Method b;
			if ((b = arrayOfMethod[i]).isAnnotationPresent(Id.class)) {
				String a = b.getName();
				return a.substring(3, 4).toLowerCase() + a.substring(4);
			}
		}
		return null;
	}

	public static int getFieldCount(Class<? extends BaseModel> entityClass) {
		int b = 0;
		Method[] arrayOfMethod;
		int j = (arrayOfMethod = entityClass.getMethods()).length;
		for (int i = 0; i < j; i++) {
			Method a;
			if (arrayOfMethod[i].isAnnotationPresent(Column.class)) {
				b++;
			}
		}
		return b;
	}

	public static Class<?> getIdFieldType(Class<? extends BaseModel> entityClass) {
		Method[] arrayOfMethod;
		int j = (arrayOfMethod = entityClass.getMethods()).length;
		for (int i = 0; i < j; i++) {
			Method a;
			if ((a = arrayOfMethod[i]).isAnnotationPresent(Id.class)) {
				return a.getReturnType();
			}
		}
		return null;
	}

	public static Class<? extends BaseQueryItem> getQueryItemClass(BaseQueryCondition condition) {
		String b = condition.getClass().getName();
		if (b.endsWith("QueryCondition")) {
			b = b.substring(0, b.length() - 9) + "Item";
			try {
				return (Class<? extends BaseQueryItem>) Class.forName(b);
			} catch (ClassNotFoundException a) {
				throw new RuntimeException("Item class for " + b + " not found");
			}

		}
		throw new RuntimeException("Item class for " + b + " not found");

	}

	public static String getSqlQueryName(Class<? extends BaseQueryCondition> conditionClass) {
		String a;
		if ((a = conditionClass.getSimpleName()).endsWith("QueryCondition")) {
			a = a.substring(0, a.length() - 9);
		}
		return a;
	}

	public static String getSqlQueryName(BaseQueryCondition condition) {
		return getSqlQueryName(condition.getClass());
	}

}
