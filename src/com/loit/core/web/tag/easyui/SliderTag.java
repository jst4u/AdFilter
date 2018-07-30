package com.loit.core.web.tag.easyui;

public class SliderTag extends BaseEasyUIDivTag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] SliderOptionNamesString = {"mode","proxy","cursor","axis","handle","style"};
	protected static String[] SliderOptionNamesBoolean = {"reversed","showTip","disabled","revert","disabled"};
	protected static String[] SliderOptionNamesNumber = {"value","min","max","step","deltaX","deltaY","edge","width","height","rule"};
	
	protected static String[] SliderEventNames = { "tipFormatter", "onChange","onSlideStart", "onSlideEnd","onBeforeDrag", "onStartDrag", "onDrag", "onStopDrag"};
	
	public SliderTag(){
		this.setTagClass("easyui-slider");
		this.setOptionNamesString(SliderOptionNamesString);
		this.setOptionNamesBoolean(SliderOptionNamesBoolean);
		this.setOptionNamesNumber(SliderOptionNamesNumber);
		this.setEventNames(SliderEventNames);
	}
}
