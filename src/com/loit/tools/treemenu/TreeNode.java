package com.loit.tools.treemenu;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import com.loit.tools.workspace.BasePanel;

public class TreeNode extends DefaultMutableTreeNode {
	private String c;
	private Class<? extends BasePanel> B;
	private Object[] b;
	private TreeNodeLoader A;
	private boolean a = false;

	public TreeNode(String name, TreeNodeLoader childrenLoader, Class<? extends BasePanel> panelClass,Object[] parameters) {
		super(name);
		this.c = name;
		this.A = childrenLoader;
		this.B = panelClass;
		this.b = parameters;
		if (childrenLoader != null)
			add(new TreeNode("loading...", null, null, new Object[0]));
	}

	public void add(MutableTreeNode newChild) {
		if ((newChild instanceof TreeNode))
			super.add(newChild);
		else
			throw new IllegalArgumentException(
					"Child node must be of type cn.walle.tools.base.treemenu.TreeNode");
	}

	public String getName() {
		return this.c;
	}

	public void setName(String name) {
		this.c = name;
	}

	public Class<? extends BasePanel> getPanelClass() {
		return this.B;
	}

	public void setPanelClass(Class<? extends BasePanel> panelClass) {
		this.B = panelClass;
	}

	public Object[] getParameters() {
		return this.b;
	}

	public void setParameters(Object[] parameters) {
		this.b = parameters;
	}

	public TreeNodeLoader getChildrenLoader() {
		return this.A;
	}

	public void setChildrenLoader(TreeNodeLoader childrenLoader) {
		this.A = childrenLoader;
	}

	public boolean isChildrenLoaded() {
		return this.a;
	}

	public void setChildrenLoaded(boolean childrenLoaded) {
		this.a = childrenLoaded;
	}

}
