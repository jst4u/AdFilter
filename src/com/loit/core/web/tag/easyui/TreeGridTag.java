package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class TreeGridTag extends DataGridTag{

	protected static final long serialVersionUID = 1L;
	
	protected static String[] TreeGridOptionNamesString = {"treeField","startId" };
	
	protected static String[] TreeGridOptionNamesBoolean = { "animate"};
	
	protected static String[] TreeGridOptionNamesNumber = {};

	protected static String[] TreeGridEventNames = {"onBeforeExpand", "onExpand", "onBeforeCollapse", "onCollapse", "onContextMenu"};
	static{
		TreeGridOptionNamesString = (String[]) ArrayUtils.addAll(TreeGridOptionNamesString, DataGridTag.DataGridOptionNamesString);
		TreeGridOptionNamesBoolean =(String[]) ArrayUtils.addAll(TreeGridOptionNamesBoolean, DataGridTag.DataGridOptionNamesBoolean);
		TreeGridOptionNamesNumber =(String[]) ArrayUtils.addAll(TreeGridOptionNamesNumber, DataGridTag.DataGridOptionNamesNumber);
		TreeGridEventNames = (String[]) ArrayUtils.addAll(TreeGridEventNames, DataGridTag.DataGridEventNames);
	}

	public TreeGridTag(){
		super();
		super.setTagClass("easyui-treegrid");
		this.setOptionNamesString(TreeGridOptionNamesString);
		this.setOptionNamesBoolean(TreeGridOptionNamesBoolean);
		this.setOptionNamesNumber(TreeGridOptionNamesNumber);
		this.setEventNames(TreeGridEventNames);
	}
	
	
}
