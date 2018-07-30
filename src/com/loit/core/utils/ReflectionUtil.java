package com.loit.core.utils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ClassUtils;
import org.objectweb.asm.ClassReader;

import edu.emory.mathcs.backport.java.util.Collections;

public class ReflectionUtil {
	private static Log logger = LogFactory.getLog(ReflectionUtil.class);

	private static Map<Class<?>, Map<Method, String[]>> classDecMethods = new HashMap();

	private static Map<Class<?>, Map<Method, String[]>> classMethods = new HashMap();

	private static Map<Class<?>, List<Method>> CalssDecMethodsInOrder = new HashMap();//b

	private static Map<Class<?>, List<Method>> CalssMethodsInOrder = new HashMap();//

	private static Map<Method, String> MethodSignatureMap = new HashMap();

	private static Map<Method, String[]> declaredMethodsParameterNames(Class<?> clazz) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getDeclaredMethodsParameterNames " + clazz);
		}
		Map methodParameter = new LinkedHashMap();
//		ClassReader classReader =null;
		try {
//			 判断参数类是否存在？
//			String classPath = "/" + clazz.getName().replace('.', '/') + ".class";
//			classReader = new ClassReader( ReflectionUtil.class.getResourceAsStream(classPath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Method[] methods = clazz.getDeclaredMethods();
		for(int i=0;i<methods.length;i++){
			List ml = ClassMethodUtil.getParamNames(methods[i]);
			String[] ms = new String[ml.size()];
			ml.toArray(ms);
			methodParameter.put(methods[i], ms);
		}
//		ClassReader e;
//		ClassVisitor c = new ClassReader(clazz, g).v{
//			public ClassVisitor v(){
//				return new ClassVisitor();
//			}
//			
//		};

//		e.accept(c, false);
//		classReader.accept(new ClassVisitor(){
//			
//		}, false);
		
		return methodParameter;
	}

	/**
	 * @param clazz
	 * @return
	 */
	public static Map<Method, String[]> getDeclaredMethodsParameterNames(Class<?> clazz) {
		if (!classDecMethods.containsKey(clazz)) {
        		synchronized (classDecMethods) {
        			if (!classDecMethods.containsKey(clazz)) {
        				 Map<Method, String[]> map  = declaredMethodsParameterNames(clazz);
        				 classDecMethods.put(clazz, map);
        				return map;
        			}
        		}
		}
		return  classDecMethods.get(clazz);
	}

	private static Map<Method, String[]> methodsParameterNames(Class<?> clazz) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMethodsParameterNames " + clazz);
		}
//		if(null == clazz){
//			return null;
//		}
		Map<Method, String[]> methodParameter = new LinkedHashMap<Method, String[]>();//f
//		
//		Method[] methods = clazz.getDeclaredMethods();
//		for(int i=0;i<methods.length;i++){
//			List ml = ClassMethodUtil.getParamNames(methods[i]);
//			String[] ms = new String[ml.size()];
//			ml.toArray(ms);
//			methodParameter.put(methods[i], ms);
//		}
		
		    Set e = new HashSet();
		    Class d = clazz;
		    
		    do{
			    Map c = getDeclaredMethodsParameterNames(d);
			      Iterator localIterator = c.keySet().iterator();
			      for (; localIterator.hasNext(); )
			      {
				      
			        Method b = (Method)localIterator.next();
			        String a = getMethodSignature(b);

			        if (!e.contains(a)) {
			          e.add(a);
			          methodParameter.put(b, (String[])c.get(b));
			        }
			      }
			    d = d.getSuperclass();
		    }while(d!=null);
		    
		return methodParameter;
	}

	/**
	 * @param clazz
	 * @return
	 */
	public static Map<Method, String[]> getMethodsParameterNames(Class<?> clazz) {
		if (!classMethods.containsKey(clazz)) {
			synchronized (classMethods) {
				if (!classMethods.containsKey(clazz)) {
					Map<Method, String[]>  map = ReflectionUtil.methodsParameterNames(clazz);
					classMethods.put(clazz, map);
					return map;
				}
			}
		}
		clazz.getMethods();
		return  classMethods.get(clazz);
	}

	private static List<Method> declaredMethodsInDeclaringOrder(Class<?> clazz) {
		if (logger.isDebugEnabled()) {
			logger.debug("getDeclaredMethodsInDeclaringOrder " + clazz);
		}
		List methodList= new ArrayList();
		
		Method[] methods = clazz.getDeclaredMethods();
		Collections.addAll(methodList, methods);

		return methodList;
	}

	public static List<Method> getDeclaredMethodsInDeclaringOrder(Class<?> clazz) {
		if (!CalssDecMethodsInOrder.containsKey(clazz)) {
			synchronized (CalssDecMethodsInOrder) {
				if (!CalssDecMethodsInOrder.containsKey(clazz)) {
					List a = declaredMethodsInDeclaringOrder(clazz);
					CalssDecMethodsInOrder.put(clazz, a);
					return a;
				}
			}
		}
		return (List) CalssDecMethodsInOrder.get(clazz);
	}

	private static List<Method> methodsInDeclaringOrder(Class<?> clazz) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMethodsInDeclaringOrder " + clazz);
		}
		List g = new ArrayList();
		Set f = new HashSet();
		Class e = clazz;
		 do{
			 List d = getDeclaredMethodsInDeclaringOrder(e);
			 List c = new ArrayList();
			Iterator localIterator;
			for (localIterator = d.iterator(); localIterator.hasNext();) {
				Method b;
				String a = getMethodSignature(b = (Method) localIterator.next());

				if (!f.contains(a)) {
					f.add(a);
					c.add(b);
				}
			}
			g.addAll(0, c);
			    e = e.getSuperclass();
		    }while(e!=null);
		 
//		tmpTernaryOp = clazz;
//		while ((e = e.getSuperclass()) != null) {
			
			
//		}

		return g;
	}

	public static List<Method> getMethodsInDeclaringOrder(Class<?> clazz) {
		if (!CalssMethodsInOrder.containsKey(clazz)) {
			synchronized (CalssMethodsInOrder) {
				if (!CalssMethodsInOrder.containsKey(clazz)) {
					List a = methodsInDeclaringOrder(clazz);
					CalssMethodsInOrder.put(clazz, a);
					return a;
				}
			}
		}
		return (List) CalssMethodsInOrder.get(clazz);
	}

	public static String getMethodSignature(Method method) {
		if (!MethodSignatureMap.containsKey(method)) {
			synchronized (MethodSignatureMap) {
				if (!MethodSignatureMap.containsKey(method)) {
					String map = ReflectionUtil.methodSignature(method);
					MethodSignatureMap.put(method,map);
					return map;
				}
			}
		}
		return MethodSignatureMap.get(method);
	}
	
	private static String methodSignature(Method method) {
		if (logger.isDebugEnabled()){
			logger.debug("getMethodSignature " + method);
		}
		StringBuilder methodSignature = new StringBuilder();
		methodSignature.append(method.getName());
		methodSignature.append("(");
		Class[] pTypes = method.getParameterTypes();//b
		if (pTypes.length > 0) {
			for (int i = 0; i <pTypes.length; i++) {
				methodSignature.append(ClassUtils.getQualifiedName(pTypes[i]));
				methodSignature.append(", ");
			}
			methodSignature.setLength(methodSignature.length() - 2);
		}
		methodSignature.append(")");
		return methodSignature.toString();
	}

	public static Type getActualTypeArgument(Class<?> clazz, TypeVariable<?> typeVariable) {
		if (!(typeVariable.getGenericDeclaration() instanceof Class)) {
			return null;
		}
		Type[] interfaceTypes = clazz.getGenericInterfaces();//arrayOfType1
		
		TypeVariable[] typeParameters = typeVariable.getGenericDeclaration().getTypeParameters();//e
		Type[] d = (Type[]) null;
		Type[] c;
		
		for (int i=0 ; i < interfaceTypes.length; i++) {
			Type type = interfaceTypes[i];
			if ((type instanceof ParameterizedType)) {
				d = ((ParameterizedType) type).getActualTypeArguments();
				break;
			}
		}
		if (d == null) {
			throw new RuntimeException("Actual type arguments not found");
		}
		if (typeParameters.length != d.length) {
			throw new RuntimeException("Wrong number of actual type arguments");
		}
		
		for (int i=0 ; i < typeParameters.length; i++) {
			if (typeParameters[i] == typeVariable) {
				return d[i];
			}
		}
		throw new RuntimeException("Actual type argument for " + typeVariable + " not found");
	}
}
