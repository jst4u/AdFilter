package com.loit.tools.fields;

// import cn.walle.core.support.SystemConfig;
// import cn.walle.core.util.ClassUtils;
// import cn.walle.core.util.ContextUtils;
// import cn.walle.tools.base.treemenu.TreeNode;
// import cn.walle.tools.base.treemenu.TreeNodeLoader;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import com.loit.core.commom.SysConfig;
import com.loit.core.spring.SpringContext;
import com.loit.core.utils.ClassUtil;
import com.loit.tools.treemenu.TreeNode;
import com.loit.tools.treemenu.TreeNodeLoader;

public class FieldDefinitionsNodeLoader implements TreeNodeLoader {
	public void loadChildren(TreeNode treeNode) throws Exception {
		SpringContext.getApplicationContext();
		File o;
		String n = new File(SysConfig.CLASS_DIR).getAbsolutePath();
		int m = n.length() + 1;

		TreeMap l = new TreeMap();

		 List<File> k = ClassUtil.getFieldDefinitionXmlFiles();
		 for (File j : k) {
			 String h = j.getParent();
			 h = h.substring(m);
			 String packageName = h.replace('/', '.').replace('\\', '.');	//f is xml's package,eg:"com.loit.system.model"	
			 
//			 TreeMap d = (TreeMap)l.get(packageName);			
//			 TreeMap c = new TreeMap();
			
//			 (l.containsKey(f) ? ??? :
//			 l.put(packageName, c);
			
			 if(l.containsKey(packageName)){
				    ((TreeSet)l.get(packageName)).add(j);
			    }else{
				    TreeSet dd= new TreeSet();
				    dd.add(j);
				    l.put(packageName, dd);
			    }
//			 c.put(j.getName(), j);
		 }
		
		 treeNode.removeAllChildren();
		 Iterator it = l.keySet().iterator();
		 
		while (it.hasNext()) {
			String packname = (String) it.next();
			// String i = (String)???.next();
			TreeNode e = new TreeNode(packname, null, null, new Object[0]);
			// TreeNode g = new TreeNode(i, null, null, new Object[0]);
			TreeSet packTreeset = (TreeSet) l.get(packname);
			Iterator iter = packTreeset.iterator();
			while (iter.hasNext()) {
				File file = (File)iter.next();
				String name = file.getName();
				TreeNode a = new TreeNode(name, null, FieldDefinitionsPanel.class, new Object[] {packname, file});
				// TreeNode a = new TreeNode(name, this.a, null, new Object[0]);
				e.add(a);
			}
			 
//			 TreeMap e = (TreeMap)l.get(i);
//			 Iterator localIterator2 = e.keySet().iterator(); 
//			 for (tmpTernaryOp = localIterator2; localIterator2.hasNext(); ) { 
//				 String b =
//				 (String)localIterator2.next();
//				 TreeNode a = new TreeNode(b, null, FieldDefinitionsPanel.class, new
//				 Object[] { i, e.get(b) });
//				 g.add(a);
//			 }
			 treeNode.add(e);
		 }
		 
		 
//		  treeNode.removeAllChildren();
//		    tmp = k.keySet().iterator();
//
//		    while(tmp.hasNext()){
//			    String packname = (String) tmp.next();
//			    TreeNode e = new TreeNode(packname, null, null, new Object[0]);
//			    Iterator iter =((TreeSet)k.get(packname)).iterator();
//			    while(iter.hasNext()){
//				    String name =  (String)iter.next();
//				    TreeNode a = new TreeNode(name, this.a, null, new Object[0]);
//				        e.add(a);
//			    }
//			    treeNode.add(e);
//		    }
	}
}
