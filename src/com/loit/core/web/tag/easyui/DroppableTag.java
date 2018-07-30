package com.loit.core.web.tag.easyui;

public class DroppableTag extends BaseEasyUIDivTag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] DroppableOptionNamesString = {"accept"};
	protected static String[] DroppableOptionNamesBoolean = {"disabled"};
	protected static String[] DroppableOptionNamesNumber = {};
	
	protected static String[] DroppableEventNames = { "onDragEnter", "onDragOver", "onDragLeave", "onDrop"};
	
	public DroppableTag(){
		this.setTagClass("easyui-droppable");
		this.setOptionNamesString(DroppableOptionNamesString);
		this.setOptionNamesBoolean(DroppableOptionNamesBoolean);
		this.setOptionNamesNumber(DroppableOptionNamesNumber);
		this.setEventNames(DroppableEventNames);
	}
}
