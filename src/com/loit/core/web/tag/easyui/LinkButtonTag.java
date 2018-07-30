package com.loit.core.web.tag.easyui;


public class LinkButtonTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] LinkButtonOptionNamesString = {"id","group", "text","iconCls","iconAlign"};
	protected static String[] LinkButtonOptionNamesBoolean = {"disabled","toggle","selected","plain"};
	protected static String[] LinkButtonOptionNamesNumber = {"deltaX","deltaY","showDelay","hideDelay"};
	
	protected static String[] LinkButtonEventNames = {};
	
	public LinkButtonTag(){
		this.setTagClass("easyui-linkbutton");
		this.setTagName("a");
		this.setOptionNamesString(LinkButtonOptionNamesString);
		this.setOptionNamesBoolean(LinkButtonOptionNamesBoolean);
		this.setOptionNamesNumber(LinkButtonOptionNamesNumber);
		this.setEventNames(LinkButtonEventNames);
	}
	
	
}
