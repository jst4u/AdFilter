package com.loit.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
 


public class ClassMethodUtil {

	 /**
	     * 获取Method的形参名称列表
	     * 
	     * @param method
	     *            需要解析的方法
	     * @return 形参名称列表,如果没有调试信息,将返回null
	     */
	    public static List<String> getParamNames(Method method) {
	        try {
	            int size = method.getParameterTypes().length;
	            if (size == 0)
	                return new ArrayList<String>(0);
	            List<String> list = getParamNames(method.getDeclaringClass()).get(
	                    getKey(method));
	            if (list != null && list.size() > size)
	                return list.subList(0, size);
	            return list;
	        } catch (Throwable e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	    /**
	     * 获取一个类的所有方法/构造方法的形参名称Map
	     * 
	     * @param klass
	     *            需要解析的类
	     * @return 所有方法/构造方法的形参名称Map
	     * @throws IOException
	     *             如果有任何IO异常,不应该有,如果是本地文件,那100%遇到bug了
	     */
	    public static Map<String, List<String>> getParamNames(Class<?> klass)
	            throws IOException {
	        InputStream in = klass.getResourceAsStream("/"
	                + klass.getName().replace('.', '/') + ".class");
	        return getParamNames(in);
	    }
	 
	    public static Map<String, List<String>> getParamNames(InputStream in)
	            throws IOException {
	        ClassReader cr = new ClassReader(in);
	        ClassNode cn = new ClassNode();
	        cr.accept(cn, ClassReader.EXPAND_FRAMES);
	        List<MethodNode> methods = cn.methods;
	        Map<String, List<String>> rtn = new HashMap<String, List<String>>();
	        for (int i = 0; i < methods.size(); ++i) {
	            List<LocalVariable> varNames = new ArrayList<LocalVariable>();
	            MethodNode method = methods.get(i);
	            List<LocalVariableNode> local_variables = method.localVariables;
	            for (int l = 0; l < local_variables.size(); l++) {
	                String varName = local_variables.get(l).name;
	                // index
	                int index = local_variables.get(l).index;
	                if (!"this".equals(varName)) // 非静态方法,第一个参数是this
	                    varNames.add(new LocalVariable(index, varName));
	            }
	            LocalVariable[] tmpArr = varNames.toArray(new LocalVariable[0]);
	            Arrays.sort(tmpArr);
	            List<String> list = new ArrayList<String>();
	            for (LocalVariable var : tmpArr) {
	                list.add(var.name);
	            }
	            rtn.put(method.name + "," + method.desc, list);
	        }
	        return rtn;
	    }
	 
	    static class LocalVariable implements Comparable<LocalVariable> {
	        public int    index;
	        public String name;
	 
	        public LocalVariable(int index, String name) {
	            this.index = index;
	            this.name = name;
	        }
	 
	        public int compareTo(LocalVariable o) {
	            return this.index - o.index;
	        }
	    }
	 
	    /**
	     * 获取Constructor的形参名称列表
	     * 
	     * @param constructor
	     *            需要解析的构造函数
	     * @return 形参名称列表,如果没有调试信息,将返回null
	     */
	    public static List<String> getParamNames(Constructor<?> constructor) {
	        try {
	            int size = constructor.getParameterTypes().length;
	            if (size == 0)
	                return new ArrayList<String>(0);
	            List<String> list = getParamNames(constructor.getDeclaringClass())
	                    .get(getKey(constructor));
	            if (list != null && list.size() != size)
	                return list.subList(0, size);
	            return list;
	        } catch (Throwable e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	    /**
	     * 传入Method或Constructor,获取getParamNames方法返回的Map所对应的key
	     */
	    public static String getKey(Object obj) {
	        StringBuilder sb = new StringBuilder();
	        if (obj instanceof Method) {
	            sb.append(((Method) obj).getName()).append(',');
	            getDescriptor(sb, (Method) obj);
	        } else if (obj instanceof Constructor) {
	            sb.append("<init>,"); // 只有非静态构造方法才能用有方法参数的,而且通过反射API拿不到静态构造方法
	            getDescriptor(sb, (Constructor<?>) obj);
	        } else
	            throw new RuntimeException("Not Method or Constructor!");
	        return sb.toString();
	    }
	 
	    public static void getDescriptor(StringBuilder sb, Method method) {
	        sb.append('(');
	        for (Class<?> klass : method.getParameterTypes())
	            getDescriptor(sb, klass);
	        sb.append(')');
	        getDescriptor(sb, method.getReturnType());
	    }
	 
	    public static void getDescriptor(StringBuilder sb,
	            Constructor<?> constructor) {
	        sb.append('(');
	        for (Class<?> klass : constructor.getParameterTypes())
	            getDescriptor(sb, klass);
	        sb.append(')');
	        sb.append('V');
	    }
	 
	    /** 本方法来源于ow2的asm库的Type类 */
	    public static void getDescriptor(final StringBuilder buf, final Class<?> c) {
	        Class<?> d = c;
	        while (true) {
	            if (d.isPrimitive()) {
	                char car;
	                if (d == Integer.TYPE) {
	                    car = 'I';
	                } else if (d == Void.TYPE) {
	                    car = 'V';
	                } else if (d == Boolean.TYPE) {
	                    car = 'Z';
	                } else if (d == Byte.TYPE) {
	                    car = 'B';
	                } else if (d == Character.TYPE) {
	                    car = 'C';
	                } else if (d == Short.TYPE) {
	                    car = 'S';
	                } else if (d == Double.TYPE) {
	                    car = 'D';
	                } else if (d == Float.TYPE) {
	                    car = 'F';
	                } else /* if (d == Long.TYPE) */{
	                    car = 'J';
	                }
	                buf.append(car);
	                return;
	            } else if (d.isArray()) {
	                buf.append('[');
	                d = d.getComponentType();
	            } else {
	                buf.append('L');
	                String name = d.getName();
	                int len = name.length();
	                for (int i = 0; i < len; ++i) {
	                    char car = name.charAt(i);
	                    buf.append(car == '.' ? '/' : car);
	                }
	                buf.append(';');
	                return;
	            }
	        }
	    }
}
