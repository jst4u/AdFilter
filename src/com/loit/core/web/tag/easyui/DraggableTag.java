package com.loit.core.web.tag.easyui;

public class DraggableTag extends BaseEasyUIDivTag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] DraggableOptionNamesString = {"proxy","cursor","axis","handle"};
	protected static String[] DraggableOptionNamesBoolean = {"revert","disabled"};
	protected static String[] DraggableOptionNamesNumber = {"deltaX","deltaY","edge","width","height"};
	
	protected static String[] DraggableEventNames = { "onBeforeDrag", "onStartDrag", "onDrag", "onStopDrag"};
	
	public DraggableTag(){
		this.setTagClass("easyui-draggable");
		this.setOptionNamesString(DraggableOptionNamesString);
		this.setOptionNamesBoolean(DraggableOptionNamesBoolean);
		this.setOptionNamesNumber(DraggableOptionNamesNumber);
		this.setEventNames(DraggableEventNames);
	}
}
