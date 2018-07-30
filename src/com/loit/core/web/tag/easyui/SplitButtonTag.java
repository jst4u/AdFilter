package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class SplitButtonTag extends BaseEasyUITag {

	private static final long serialVersionUID = 1L;

	protected static String[] MenuButtonOptionNamesString = { "menu", "iconCls","href","id"};
	protected static String[] MenuButtonOptionNamesBoolean = { "duration" };
	protected static String[] MenuButtonOptionNamesNumber = { "plain" };

	protected static String[] MenuButtonEventNames = { };

	static{
		MenuButtonOptionNamesString = (String[]) ArrayUtils.addAll(MenuButtonOptionNamesString, MenuTag.MenuOptionNamesString);
		MenuButtonOptionNamesBoolean =(String[]) ArrayUtils.addAll(MenuButtonOptionNamesBoolean, MenuTag.MenuOptionNamesBoolean);
		MenuButtonOptionNamesNumber =(String[]) ArrayUtils.addAll(MenuButtonOptionNamesNumber, MenuTag.MenuOptionNamesNumber);
		MenuButtonEventNames = (String[]) ArrayUtils.addAll(MenuButtonEventNames, MenuTag.MenuEventNames);
	}
	
	public SplitButtonTag() {
		super.setTagClass("easyui-splitbutton");
		super.setTagName("a");
		this.setOptionNamesString(MenuButtonOptionNamesString);
		this.setOptionNamesBoolean(MenuButtonOptionNamesBoolean);
		this.setOptionNamesNumber(MenuButtonOptionNamesNumber);
		this.setEventNames(MenuButtonEventNames);
	}
	
	
}
