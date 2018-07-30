package com.loit.core.web.tag.easyui;

public class ProgressBarTag extends BaseEasyUIDivTag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] ProgressBarOptionNamesString = {"width","text","id","style"};
	protected static String[] ProgressBarOptionNamesBoolean = {};
	protected static String[] ProgressBarOptionNamesNumber = {"height","value"};
	
	protected static String[] ProgressBarEventNames = { "onChange"};
	
	public ProgressBarTag(){
		this.setTagClass("easyui-progressbar");
		this.setOptionNamesString(ProgressBarOptionNamesString);
		this.setOptionNamesBoolean(ProgressBarOptionNamesBoolean);
		this.setOptionNamesNumber(ProgressBarOptionNamesNumber);
		this.setEventNames(ProgressBarEventNames);
	}
}
