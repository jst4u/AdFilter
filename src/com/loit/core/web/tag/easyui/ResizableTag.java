package com.loit.core.web.tag.easyui;

public class ResizableTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] ResizableOptionNamesString = {"handles"};
	protected static String[] ResizableOptionNamesBoolean = {"disabled"};
	protected static String[] ResizableOptionNamesNumber = {"minWidth","minHeight","maxWidth","maxHeight","edge"};
	
	protected static String[] ResizableEventNames = { "onStartResize", "onResize", "onStopResize"};
	
	public ResizableTag(){
		this.setTagClass("easyui-resizable");
		this.setOptionNamesString(ResizableOptionNamesString);
		this.setOptionNamesBoolean(ResizableOptionNamesBoolean);
		this.setOptionNamesNumber(ResizableOptionNamesNumber);
		this.setEventNames(ResizableEventNames);
	}

}
