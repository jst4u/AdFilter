package com.loit.core.web.tag.easyui;

public class MenuTag extends BaseEasyUIDivTag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] MenuOptionNamesString = {"id","text", "iconCls","href"};
	protected static String[] MenuOptionNamesBoolean = {"disabled"};
	protected static String[] MenuOptionNamesNumber = {"zIndex","left", "top","minWidth"};
	
	protected static String[] MenuEventNames = { "onclick","onShow","onHide","onClick"};
	
	public MenuTag(){
		this.setTagClass("easyui-menu");
		this.setOptionNamesString(MenuOptionNamesString);
		this.setOptionNamesBoolean(MenuOptionNamesBoolean);
		this.setOptionNamesNumber(MenuOptionNamesNumber);
		this.setEventNames(MenuEventNames);
	}

}
