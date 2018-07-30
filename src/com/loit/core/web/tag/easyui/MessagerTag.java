package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class MessagerTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] MessagerOptionNamesString = {"ok","cancle"};
	protected static String[] MessagerOptionNamesBoolean = {};
	protected static String[] MessagerOptionNamesNumber = {};
	protected static String[] MessagerEventNames = {};
	
	static{
		MessagerOptionNamesString = (String[]) ArrayUtils.addAll(MessagerOptionNamesString, WindowTag.WindowOptionNamesString);
		MessagerOptionNamesBoolean =(String[]) ArrayUtils.addAll(MessagerOptionNamesBoolean, WindowTag.WindowOptionNamesBoolean);
		MessagerOptionNamesNumber =(String[]) ArrayUtils.addAll(MessagerOptionNamesNumber, WindowTag.WindowOptionNamesNumber);
		MessagerEventNames = (String[]) ArrayUtils.addAll(MessagerEventNames, WindowTag.WindowEventNames);
		
		MessagerOptionNamesString = (String[]) ArrayUtils.addAll(MessagerOptionNamesString, LinkButtonTag.LinkButtonOptionNamesString);
		MessagerOptionNamesBoolean =(String[]) ArrayUtils.addAll(MessagerOptionNamesBoolean, LinkButtonTag.LinkButtonOptionNamesBoolean);
		MessagerOptionNamesNumber =(String[]) ArrayUtils.addAll(MessagerOptionNamesNumber, LinkButtonTag.LinkButtonOptionNamesNumber);
		MessagerEventNames = (String[]) ArrayUtils.addAll(MessagerEventNames, LinkButtonTag.LinkButtonEventNames);
		
		MessagerOptionNamesString = (String[]) ArrayUtils.addAll(MessagerOptionNamesString, ProgressBarTag.ProgressBarOptionNamesString);
		MessagerOptionNamesBoolean =(String[]) ArrayUtils.addAll(MessagerOptionNamesBoolean, ProgressBarTag.ProgressBarOptionNamesBoolean);
		MessagerOptionNamesNumber =(String[]) ArrayUtils.addAll(MessagerOptionNamesNumber, ProgressBarTag.ProgressBarOptionNamesNumber);
		MessagerEventNames = (String[]) ArrayUtils.addAll(MessagerEventNames, ProgressBarTag.ProgressBarEventNames);
	}
	
	public MessagerTag(){
		this.setTagClass("easyui-linkbutton");
		this.setTagName("a");
		this.setOptionNamesString(MessagerOptionNamesString);
		this.setOptionNamesBoolean(MessagerOptionNamesBoolean);
		this.setOptionNamesNumber(MessagerOptionNamesNumber);
		this.setEventNames(MessagerEventNames);
	}

}
