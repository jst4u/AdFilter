package com.loit.core.utils;

import java.io.InputStream;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.springframework.util.ClassUtils;

public class ReflectionUtils {
//	private static Log C = LogFactory.getLog(ReflectionUtils.class);
//
//	private static Map<Class<?>, Map<Method, String[]>> c = new HashMap();
//
//	private static Map<Class<?>, Map<Method, String[]>> B = new HashMap();
//
//	private static Map<Class<?>, List<Method>> b = new HashMap();
//
//	private static Map<Class<?>, List<Method>> A = new HashMap();
//
//	private static Map<Method, String> a = new HashMap();
//
//	private static Map<Method, String[]> B(Class<?> clazz) {
//		if (C.isDebugEnabled()) {
//			C.debug("getDeclaredMethodsParameterNames " + clazz);
//		}
//		Map g = new LinkedHashMap();
//		try {
//			String d = "/" + clazz.getName().replace('.', '/') + ".class";
//			InputStream a = ReflectionUtils.class.getResourceAsStream(d);
//			f = new ClassReader(a);
//		} catch (Exception b) {
//			ClassReader f;
//			throw new RuntimeException(b);
//		}
//		ClassReader e;
//		ClassVisitor c = new e(clazz, g);
//
//		e.accept(c, false);
//		return g;
//	}
//
//	public static Map<Method, String[]> getDeclaredMethodsParameterNames(Class<?> clazz) {
//		if (!c.containsKey(clazz)) {
//			synchronized (c) {
//				if (!c.containsKey(clazz)) {
//					Map a = B(clazz);
//					c.put(clazz, a);
//					return a;
//				}
//			}
//		}
//		return (Map) c.get(clazz);
//	}
//
//	private static Map<Method, String[]> b(Class<?> clazz) {
//		if (C.isDebugEnabled()) {
//			C.debug("getMethodsParameterNames " + clazz);
//		}
//		Map f = new LinkedHashMap();
//		Set e = new HashSet();
//		Class d = clazz;
//		tmpTernaryOp = clazz;
//		while ((d = d.getSuperclass()) != null) {
//			Map c;
//			Iterator localIterator;
//			for (tmpTernaryOp = (localIterator = (c = getDeclaredMethodsParameterNames(d)).keySet().iterator()); localIterator.hasNext();) {
//				Method b;
//				String a = getMethodSignature(b = (Method) localIterator.next());
//
//				if (!e.contains(a)) {
//					e.add(a);
//					f.put(b, (String[]) c.get(b));
//				}
//			}
//		}
//
//		return f;
//	}
//
//	public static Map<Method, String[]> getMethodsParameterNames(Class<?> clazz) {
//		if (!B.containsKey(clazz)) {
//			synchronized (B) {
//				if (!B.containsKey(clazz)) {
//					Map a = b(clazz);
//					B.put(clazz, a);
//					return a;
//				}
//			}
//		}
//		return (Map) B.get(clazz);
//	}
//
//	private static List<Method> A(Class<?> clazz) {
//		if (C.isDebugEnabled()) {
//			C.debug("getDeclaredMethodsInDeclaringOrder " + clazz);
//		}
//		List g = new ArrayList();
//		try {
//			String d = "/" + clazz.getName().replace('.', '/') + ".class";
//			InputStream a = ReflectionUtils.class.getResourceAsStream(d);
//			f = new ClassReader(a);
//		} catch (Exception b) {
//			ClassReader f;
//			throw new RuntimeException("Error read " + clazz, b);
//		}
//		ClassReader e;
//		ClassVisitor c = new b(clazz, g);
//
//		e.accept(c, false);
//		return g;
//	}
//
//	public static List<Method> getDeclaredMethodsInDeclaringOrder(Class<?> clazz) {
//		if (!b.containsKey(clazz)) {
//			synchronized (b) {
//				if (!b.containsKey(clazz)) {
//					List a = A(clazz);
//					b.put(clazz, a);
//					return a;
//				}
//			}
//		}
//		return (List) b.get(clazz);
//	}
//
//	private static List<Method> a(Class<?> clazz) {
//		if (C.isDebugEnabled()) {
//			C.debug("getMethodsInDeclaringOrder " + clazz);
//		}
//		List g = new ArrayList();
//		Set f = new HashSet();
//		Class e = clazz;
//		tmpTernaryOp = clazz;
//		while ((e = e.getSuperclass()) != null) {
//			List d = getDeclaredMethodsInDeclaringOrder(e);
//			List c = new ArrayList();
//			Iterator localIterator;
//			for (tmpTernaryOp = (localIterator = d.iterator()); localIterator.hasNext();) {
//				Method b;
//				String a = getMethodSignature(b = (Method) localIterator.next());
//
//				if (!f.contains(a)) {
//					f.add(a);
//					c.add(b);
//				}
//			}
//			g.addAll(0, c);
//		}
//
//		return g;
//	}
//
//	public static List<Method> getMethodsInDeclaringOrder(Class<?> clazz) {
//		if (!A.containsKey(clazz)) {
//			synchronized (A) {
//				if (!A.containsKey(clazz)) {
//					List a = a(clazz);
//					A.put(clazz, a);
//					return a;
//				}
//			}
//		}
//		return (List) A.get(clazz);
//	}
//
//	private static String a(Method method) {
//		if (C.isDebugEnabled())
//			C.debug("getMethodSignature " + method);
//		StringBuilder c;
//		(c = new StringBuilder()).append(method.getName());
//		c.append("(");
//		Class[] b;
//		if ((b = method.getParameterTypes()).length > 0) {
//			Class[] arrayOfClass1;
//			int j = (arrayOfClass1 = b).length;
//			int i;
//			for (tmpTernaryOp = (i = 0); i < j; i++) {
//				Class a = arrayOfClass1[i];
//				c.append(ClassUtils.getQualifiedName(a));
//				c.append(", ");
//			}
//			c.setLength(c.length() - 2);
//		}
//		c.append(")");
//		return c.toString();
//	}
//
//	public static String getMethodSignature(Method method) {
//		if (!a.containsKey(method)) {
//			synchronized (a) {
//				if (!a.containsKey(method)) {
//					String a = a(method);
//					a.put(method, a);
//					return a;
//				}
//			}
//		}
//		return (String) a.get(method);
//	}
//
//	public static Type getActualTypeArgument(Class<?> clazz, TypeVariable<?> typeVariable) {
//		if (!(typeVariable.getGenericDeclaration() instanceof Class)) {
//			return null;
//		}
//		Type[] interfaceTypes = clazz.getGenericInterfaces();//arrayOfType1
//		
//		TypeVariable[] typeParameters = typeVariable.getGenericDeclaration().getTypeParameters();//e
//		Type[] actualType = null;
//		
//		for (int i=0 ; i < interfaceTypes.length; i++) {
//			Type type = interfaceTypes[i];
//			if ((type instanceof ParameterizedType)) {
//				actualType = ((ParameterizedType) type).getActualTypeArguments();
//				break;
//			}
//		}
//		if (actualType == null) {
//			throw new RuntimeException("Actual type arguments not found");
//		}
//		if (typeParameters.length != actualType.length) {
//			throw new RuntimeException("Wrong number of actual type arguments");
//		}
//		
//		for (int i=0 ; i < typeParameters.length; i++) {
//			if (typeParameters[i] == typeVariable) {
//				return actualType[i];
//			}
//		}
//		throw new RuntimeException("Actual type argument for " + typeVariable + " not found");
//	}
}