package com.loit.tools.testclient;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.loit.core.utils.ReflectionUtil;
import com.loit.tools.treemenu.TreeNode;
import com.loit.tools.treemenu.TreeNodeLoader;

public class MethodNodeLoader implements TreeNodeLoader {
	public void loadChildren(TreeNode treeNode) throws Exception {
		String className = treeNode.getName();//r
		String packageName = ((TreeNode) treeNode.getParent()).getName();//q
		Class clazz = Class.forName(packageName + "." + className);//p
		Class o;
//		className = className.endsWith("Impl")?className: className + "Impl";
		Map<Method,String[]> methodParams = ReflectionUtil.getDeclaredMethodsParameterNames(Class.forName(packageName + "." + className ));//n

		Map<String,String[]>  paramsMap = new LinkedHashMap<String,String[]> ();//m

		for (Method method : methodParams.keySet()) {
			String methodName = ReflectionUtil.getMethodSignature(method);
			String[] paramsNames = (String[]) methodParams.get(method);
			if (!paramsMap.containsKey(methodName)) {
				paramsMap.put(methodName, paramsNames);
			}
		}

		Method[] k = clazz.getMethods();
		Map<String,Method> j = new HashMap<String,Method>();
		Method[] arrayOfMethod1;
//		String[] arrayOfString1 = (arrayOfMethod1 = k).length;
//		String[] f = 0;
		for(int i=0;i<k.length;i++){
//			k[i];
			j.put(ReflectionUtil.getMethodSignature(k[i]), k[i]);
		}
//		for (tmpTernaryOp = f; f < arrayOfString1; f++) {
//			Method h = arrayOfMethod1[f];
//			((Map) j).put(ReflectionUtil.getMethodSignature(h), h);
//		}

		treeNode.removeAllChildren();

		Iterator<String> methodNames = paramsMap.keySet().iterator();
		while(methodNames.hasNext()){
			String mName = methodNames.next();
			if(j.containsKey(mName)){
				Method e =  j.get(mName);
				String[] d = (String[]) paramsMap.get(mName);
				if (d == null) {
					d = new String[e.getParameterTypes().length];
					for (int i=0; i < d.length; i++) {
						d[i] = ("arg" + i);
					}
				}
				String b = e.getName() + "(" + StringUtils.arrayToDelimitedString(d, ", ") + ")";
				TreeNode a = new TreeNode(b, null, TestClientPanel.class, new Object[] { clazz, e, d });
				treeNode.add(a);
			}
		}
	}
}