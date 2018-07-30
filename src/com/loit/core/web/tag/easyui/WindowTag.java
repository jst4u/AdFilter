package com.loit.core.web.tag.easyui;

public class WindowTag extends BaseEasyUIDivTag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] WindowOptionNamesString = {"title","style","iconCls","tools"};
	protected static String[] WindowOptionNamesBoolean = {"collapsible","minimizable","maximizable","closable","closed",
		"draggable","resizable","shadow","inline","modal"};
	protected static String[] WindowOptionNamesNumber = {"zIndex", "width", "height", "left", "top"};
	protected static String[] WindowEventNames = {"onLoad", "onBeforeOpen", "onOpen", "onBeforeClose", "onClose", "onBeforeDestroy", "onDestroy",
		"onBeforeCollapse", "onCollapse", "onBeforeExpand", "onExpand", "onResize", "onMove", "onMaximize", "onRestore", "onMinimize"};
	
	public WindowTag(){
		this.setTagClass("easyui-window");
		this.setOptionNamesString(WindowOptionNamesString);
		this.setOptionNamesBoolean(WindowOptionNamesBoolean);
		this.setOptionNamesNumber(WindowOptionNamesNumber);
		this.setEventNames(WindowEventNames);
	}

}
