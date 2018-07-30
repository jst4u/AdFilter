package com.loit.tools.testclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import com.loit.core.spring.CommonManager;
import com.loit.core.spring.SpringContext;
import com.loit.core.utils.SpringBeanUtils;
import com.loit.tools.treemenu.TreeNode;
import com.loit.tools.treemenu.TreeNodeLoader;

public class TestClientNodeLoader implements TreeNodeLoader {
	private TreeNodeLoader a = new MethodNodeLoader();

	@Override
	public void loadChildren(TreeNode treeNode) throws Exception {
		 Map extendsCM = SpringContext.getBeansOfType(CommonManager.class);//

		    List m = new ArrayList();
		   Iterator  beansIterator = extendsCM.values().iterator();
		   while(beansIterator.hasNext()){
			    CommonManager manager = (CommonManager)beansIterator.next();
			    Class  clazz = SpringBeanUtils.RealClassForCGLIBean(manager.getClass());//manager.getClass();//h
			    m.add(clazz);
		    }

		    TreeMap k = new TreeMap();
		    Iterator tmp = m.iterator();
		    while(tmp.hasNext()){
			    Class clazz =(Class) tmp.next();
			    String name = clazz.getPackage().getName();
			    
			    if(k.containsKey(name)){
				    ((TreeSet)k.get(name)).add(clazz.getSimpleName());
			    }else{
				    TreeSet d= new TreeSet();
				    d.add(clazz.getSimpleName());
				    k.put(name,d );
			    }
		    }
		    treeNode.removeAllChildren();
		    tmp = k.keySet().iterator();

		    while(tmp.hasNext()){
			    String packname = (String) tmp.next();
			    TreeNode e = new TreeNode(packname, null, null, new Object[0]);
			    Iterator iter =((TreeSet)k.get(packname)).iterator();
			    while(iter.hasNext()){
				    String name =  (String)iter.next();
				    TreeNode a = new TreeNode(name, this.a, null, new Object[0]);
				        e.add(a);
			    }
			    treeNode.add(e);
		    }
	}

}