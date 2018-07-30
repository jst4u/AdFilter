package com.loit.tools.treemenu;

public abstract interface TreeNodeLoader {
	public abstract void loadChildren(TreeNode paramTreeNode)
			    throws Exception;
}
