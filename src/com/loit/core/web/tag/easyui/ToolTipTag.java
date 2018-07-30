package com.loit.core.web.tag.easyui;

public class ToolTipTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] ToolTipOptionNamesString = {"position","content", "showEvent","hideEvent"};
	protected static String[] ToolTipOptionNamesBoolean = {"trackMouse"};
	protected static String[] ToolTipOptionNamesNumber = {"deltaX","deltaY","showDelay","hideDelay"};
	
	protected static String[] ToolTipEventNames = { "onShow","onHide","onUpdate","onPosition","onDestroy"};
	
	public ToolTipTag(){
		this.setTagClass("easyui-tooltip");
		this.setOptionNamesString(ToolTipOptionNamesString);
		this.setOptionNamesBoolean(ToolTipOptionNamesBoolean);
		this.setOptionNamesNumber(ToolTipOptionNamesNumber);
		this.setEventNames(ToolTipEventNames);
	}

}
