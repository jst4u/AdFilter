package com.loit.core.utils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.loit.core.commom.SysConfig;

public class ClassUtil {

	private static Log logger;
	private static ResourcePatternResolver A = new PathMatchingResourcePatternResolver();
	private static MetadataReaderFactory a = new CachingMetadataReaderFactory(A);

	public ClassUtil() {
	}

	public static List<Class<?>> getModelClasses() throws Exception {
		List list = new ArrayList();
		String[] arrayOfString1 = StringUtils.commaDelimitedListToStringArray(SysConfig.BASE_PACKAGE);
		for (int i = 0; i < arrayOfString1.length; i++) {
			String g = arrayOfString1[i];
			String f = "classpath*:"
					+ ClassUtils.convertClassNameToResourcePath(g)
					+ "/**/model/*Model.class";
			Resource[] arrayOfResource1 = A.getResources(f);
			for (int k = 0; k < arrayOfResource1.length; k++) {
				Resource d = arrayOfResource1[k];
				MetadataReader c = a.getMetadataReader(d);
				String b = c.getClassMetadata().getClassName();
				Class a = Class.forName(b);
				list.add(a);
			}
		}
		return list;
	}
	
	public static Object getClassInstance(String name) {
		try {
			return Thread.currentThread().getContextClassLoader()
					.loadClass(name).newInstance();
		} catch (Exception e) {
		}
		try {
			return Class.forName(name).newInstance();
		} catch (ClassNotFoundException ex) {
			logger.error(ex.toString());
			return null;
		} catch (IllegalAccessException ex) {
			logger.error(ex.toString());
			return null;
		} catch (InstantiationException ex) {
			logger.error(ex.toString());
		}
		return null;
	}

	// public static Collection getEntities(HttpServletRequest req, Class
	// entity) {
	// try {
	// return Convert.getCollection(req, entity, null);
	// } catch (IllegalAccessException ex) {
	// logger.error(ex.toString());
	// return null;
	// } catch (InstantiationException ex) {
	// logger.error(ex.toString());
	// return null;
	// } catch (InvocationTargetException ex) {
	// logger.error(ex.toString());
	// }
	// return null;
	// }

	/**
	 * 取得名称为formName得form中得实体集合
	 * 
	 * @param req
	 * @param entity
	 * @param formName
	 * @return
	 */
	// public static Collection getEntities(HttpServletRequest req, Class
	// entity, String formName) {
	// try {
	// return Convert.getCollection(req, entity, formName);
	// } catch (IllegalAccessException ex) {
	// logger.error(ex.toString());
	// return null;
	// } catch (InstantiationException ex) {
	// logger.error(ex.toString());
	// return null;
	// } catch (InvocationTargetException ex) {
	// logger.error(ex.toString());
	// }
	// return null;
	// }

	public static void copyProperties(Object from, Object to) throws Exception {
		if (from == null)
			return;
		PropertyDescriptor origDescriptors[] = PropertyUtils
				.getPropertyDescriptors(from);
		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			if (!"class".equals(name) && PropertyUtils.isReadable(from, name)
					&& PropertyUtils.isWriteable(to, name))
				try {
					Object value = PropertyUtils.getSimpleProperty(from, name);
					ClassUtil.copyProperty(to, name, value);
				} catch (NoSuchMethodException e) {
				}
		}
	}

	public static void copyProperties(Map from, Object to) throws Exception {
		if (from == null)
			return;
		if (from == null)
			return;
		for (Iterator names = ((Map) from).keySet().iterator(); names.hasNext();) {
			String name = (String) names.next();
			if (PropertyUtils.isWriteable(to, name)) {
				Object value = ((Map) from).get(name);
				ClassUtil.copyProperty(to, name, value);
			}
		}
	}

	public static void copyProperties(DynaBean from, Object to)
			throws Exception {
		if (from == null)
			return;
		DynaProperty origDescriptors[] = from.getDynaClass()
				.getDynaProperties();
		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			if (PropertyUtils.isWriteable(to, name)) {
				Object value = from.get(name);
				ClassUtil.copyProperty(to, name, value);
			}
		}
	}

	public static void resetForm(Object orig) {
		try {
			if (orig == null)
				return;
			if (orig instanceof DynaBean) {
				DynaProperty origDescriptors[] = ((DynaBean) orig)
						.getDynaClass().getDynaProperties();
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if (PropertyUtils.isWriteable(orig, name)) {
						copyProperty(orig, name, null);
					}
				}

			} else if (orig instanceof Map) {
				for (Iterator names = ((Map) orig).keySet().iterator(); names
						.hasNext();) {
					String name = (String) names.next();
					if (PropertyUtils.isWriteable(orig, name)) {
						copyProperty(orig, name, null);
					}
				}

			} else {
				PropertyDescriptor origDescriptors[] = PropertyUtils
						.getPropertyDescriptors(orig);
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if (!"class".equals(name)
							&& PropertyUtils.isReadable(orig, name)
							&& PropertyUtils.isWriteable(orig, name))
						copyProperty(orig, name, null);
				}

			}
		} catch (Exception ex) {
			// logger.error(ex.toString());
			ex.printStackTrace();
		}
	}

	public static String getProperties(Object bean) throws Exception {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		Method methodsOfBean[] = bean.getClass().getMethods();
		String property = null;
		String objStr = null;
		String nameGet = null;
		String nameGetAttr = null;
		for (int i = 0; i < methodsOfBean.length; i++) {
			nameGet = methodsOfBean[i].getName().substring(0, 3);
			nameGetAttr = methodsOfBean[i].getName().substring(3);
			if ("get".equalsIgnoreCase(nameGet)
					&& !"class".equalsIgnoreCase(nameGetAttr)
					&& !"SERVLETWRAPPER".equalsIgnoreCase(nameGetAttr
							.toUpperCase())
					&& !"MULTIPARTREQUESTHANDLER".equalsIgnoreCase(nameGetAttr
							.toUpperCase())) {
				Object args = methodsOfBean[i].invoke(bean, null);
				if (args != null)
					objStr = args.toString();
				else
					objStr = "null";
				property = getObj(args, objStr);
				if (index == 0) {
					sb.append(nameGetAttr.toUpperCase() + "=" + property);
					index++;
				} else {
					sb.append("," + nameGetAttr.toUpperCase() + "=" + property);
				}
			}
		}

		return sb.toString();
	}

	private static String getArray(Object obj[]) {
		StringBuffer sb = new StringBuffer(512);
		String objStr = null;
		for (int index = 0; index < obj.length; index++) {
			objStr = obj[index].toString();
			String property = getObj(obj[index], objStr);
			if (index == 0)
				sb.append("[" + index + "]=" + property);
			else
				sb.append(",[" + index + "]=" + property);
		}

		return sb.toString();
	}

	private static String getMap(Map map) {
		return getIterator(map.values().iterator());
	}

	private static String getIterator(Iterator iterator) {
		StringBuffer sb = new StringBuffer(512);
		String property = null;
		String objStr = null;
		int index = 0;
		for (Iterator it = iterator; it.hasNext();) {
			Object obj = it.next();
			objStr = obj.toString();
			property = getObj(obj, objStr);
			if (index == 0) {
				sb.append(property);
				index++;
			} else {
				sb.append("," + property);
			}
		}

		return sb.toString();
	}

	private static String getObj(Object obj, String property) {
		return property;
	}

	// public static synchronized void trimProperties(Object bean) {
	// Method methods[] = bean.getClass().getMethods();
	// String nameGet = null;
	// String nameGetAttr = null;
	// Class tmpTypes[] = new Class[methods.length];
	// String tmpNames[] = new String[methods.length];
	// Method tmpSetMethods[] = new Method[methods.length];
	// int index = 0;
	// for (int i = 0; i < methods.length; i++) {
	// nameGet = methods[i].getName().substring(0, 3);
	// nameGetAttr = methods[i].getName().substring(3);
	// if ("set".equalsIgnoreCase(nameGet)) {
	// tmpTypes[index] = methods[i].getParameterTypes()[0];
	// tmpNames[index] = nameGetAttr;
	// tmpSetMethods[index] = methods[i];
	// index++;
	// }
	// }
	//
	// Class types[] = new Class[index];
	// String names[] = new String[index];
	// Method setMethods[] = new Method[index];
	// for (int i = 0; i < index; i++) {
	// types[i] = tmpTypes[i];
	// names[i] = tmpNames[i];
	// setMethods[i] = tmpSetMethods[i];
	// }
	//
	// Method methodsOfFrom[] = bean.getClass().getMethods();
	// Object arg = null;
	// Object encoded = null;
	// try {
	// for (int i = 0; i < names.length; i++) {
	// for (int j = 0; j < methodsOfFrom.length; j++) {
	// nameGet = methodsOfFrom[j].getName().substring(0, 3);
	// nameGetAttr = methodsOfFrom[j].getName().substring(3);
	// if (names[i].equalsIgnoreCase(nameGetAttr) &&
	// "get".equalsIgnoreCase(nameGet)) {
	// Class returnType = methodsOfFrom[j].getReturnType();
	// if (returnType.equals(java.lang.String.class)) {
	// arg = methodsOfFrom[j].invoke(bean, null);
	// encoded = Convert.convert(arg != null ? ((String) arg).trim() : null,
	// types[i]);
	// setMethods[i].invoke(bean, new Object[] { encoded });
	// }
	// }
	// }
	//
	// }
	//
	// } catch (InvocationTargetException ex) {
	// logger.error(ex.toString());
	// } catch (IllegalArgumentException ex) {
	// logger.error(ex.toString());
	// } catch (IllegalAccessException ex) {
	// logger.error(ex.toString());
	// }
	// }

	static {
		logger = LogFactory.getLog(ClassUtil.class);
	}

	/**
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 * @throws java.lang.IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

		Object target = bean;
		int delim = name.lastIndexOf(46);
		if (delim >= 0) {
			try {
				target = PropertyUtils.getProperty(bean,
						name.substring(0, delim));
			} catch (NoSuchMethodException e) {
				return;
			}
			name = name.substring(delim + 1);
		}
		String propName = null;
		Class type = null;
		int index = -1;
		String key = null;
		propName = name;
		int i = propName.indexOf(91);
		if (i >= 0) {
			int k = propName.indexOf(93);
			try {
				index = Integer.parseInt(propName.substring(i + 1, k));
			} catch (NumberFormatException e) {
			}
			propName = propName.substring(0, i);
		}
		int j = propName.indexOf(40);
		if (j >= 0) {
			int k = propName.indexOf(41);
			try {
				key = propName.substring(j + 1, k);
			} catch (IndexOutOfBoundsException e) {
			}
			propName = propName.substring(0, j);
		}
		if (target instanceof DynaBean) {
			DynaClass dynaClass = ((DynaBean) target).getDynaClass();
			DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
			if (dynaProperty == null)
				return;
			type = dynaProperty.getType();
		} else {
			PropertyDescriptor descriptor = null;
			try {
				descriptor = PropertyUtils.getPropertyDescriptor(target, name);
				if (descriptor == null)
					return;
			} catch (NoSuchMethodException e) {
				return;
			}
			type = descriptor.getPropertyType();
			if (type == null) {
				return;
			}
		}
		if (index >= 0) {
			Converter converter = ConvertUtils.lookup(type.getComponentType());
			if (converter != null) {
				value = converter.convert(type, value);
			}
			try {
				PropertyUtils
						.setIndexedProperty(target, propName, index, value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		} else if (key != null) {
			try {
				PropertyUtils.setMappedProperty(target, propName, key, value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		} else {
			String typename = type.getName();
			if (!typename.equals("java.util.Date")
					&& !typename.equals("java.sql.Date")) {
				Converter converter = ConvertUtils.lookup(type);
				if (converter != null) {
					value = converter.convert(type, value);
				}
			}
			try {
				setSimpleProperty(target, propName, value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		}
	}

	public static void setSimpleProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (bean == null)
			throw new IllegalArgumentException("No bean specified");
		if (name == null)
			throw new IllegalArgumentException("No name specified");
		if (name.indexOf(46) >= 0)
			throw new IllegalArgumentException(
					"Nested property names are not allowed");
		if (name.indexOf(91) >= 0)
			throw new IllegalArgumentException(
					"Indexed property names are not allowed");
		if (name.indexOf(40) >= 0)
			throw new IllegalArgumentException(
					"Mapped property names are not allowed");
		PropertyDescriptor descriptor;

		descriptor = PropertyUtils.getPropertyDescriptor(bean, name);
		if (descriptor == null)
			throw new NoSuchMethodException("Unknown property '" + name + "'");
		Method writeMethod = PropertyUtils.getWriteMethod(descriptor);
		if (writeMethod == null) {
			throw new NoSuchMethodException("Property '" + name
					+ "' has no setter method");
		} else {
			if (value != null) {
				long times = 0;
				if (value.getClass().getName().equals("java.sql.Date")) {
					times = ((java.sql.Date) value).getTime();
				}
				if (value.getClass().getName().equals("java.util.Date")) {
					times = ((java.util.Date) value).getTime();
				}

				if (descriptor.getPropertyType().getName()
						.equals("java.sql.Date")) {
					value = new java.sql.Date(times);
				}
				if (descriptor.getPropertyType().getName()
						.equals("java.util.Date")) {
					value = new java.util.Date(times);
				}
			}
			Object values[] = new Object[1];
			values[0] = value;
			writeMethod.invoke(bean, values);
			return;
		}
	}

	/**
	 * 获取fields文件列表
	 * @return
	 * @throws Exception
	 */
	public static List<File> getFieldDefinitionXmlFiles() throws Exception {
		List<File> f = new ArrayList<File>();
		String[] arrayOfString1 = StringUtils.commaDelimitedListToStringArray(SysConfig.BASE_PACKAGE);
		int j = arrayOfString1.length;
		for (int i = 0; i < j; i++) {
			String d = arrayOfString1[i];
			String c = "classpath*:" + ClassUtils.convertClassNameToResourcePath(d) + "/**/model/*Fields.xml";
			Resource[] b = A.getResources(c);
			for (Resource a : b) {
				try {
					f.add(a.getFile());
				} catch (FileNotFoundException e) {
					System.out.println(e);
				}
			}
		}
		return f;
	}

	public static void main(String[] arg) {
		System.out.println((char) 46);
	}

}