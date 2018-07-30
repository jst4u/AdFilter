package com.loit.core.web.tag.easyui;

public class DialogTag extends BaseEasyUIDivTag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] DialogOptionNamesString = {"title","style","iconCls","tools","buttons"};
	protected static String[] DialogOptionNamesBoolean = {"collapsible","minimizable","maximizable","closable","closed",
		"draggable","resizable","shadow","inline","modal"};
	protected static String[] DialogOptionNamesNumber = {"zIndex", "width", "height", "left", "top"};
	
	protected static String[] DialogEventNames = {"toolbar","onLoad", "onBeforeOpen", "onOpen", "onBeforeClose", "onClose", "onBeforeDestroy", "onDestroy",
		"onBeforeCollapse", "onCollapse", "onBeforeExpand", "onExpand", "onResize", "onMove", "onMaximize", "onRestore", "onMinimize"};
	
	public DialogTag(){
		this.setTagClass("easyui-dialog");
		this.setOptionNamesString(DialogOptionNamesString);
		this.setOptionNamesBoolean(DialogOptionNamesBoolean);
		this.setOptionNamesNumber(DialogOptionNamesNumber);
		this.setEventNames(DialogEventNames);
	}

}
