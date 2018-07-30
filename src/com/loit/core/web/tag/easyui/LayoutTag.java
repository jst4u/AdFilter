package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class LayoutTag extends BaseEasyUIDivTag {
	
	/**
	 * Dependencies
	 * panel
	 * resizable
	 */
	private static final long serialVersionUID = 1L;

	private static String[] LayoutOptionNamesString = {};
	private static String[] LayoutOptionNamesBoolean = {"fit"};
	private static String[] LayoutOptionNamesNumber = {};
	private static String[] LayoutEventNames = {};
	
	static{
		LayoutOptionNamesString = (String[]) ArrayUtils.addAll(LayoutOptionNamesString, PanelTag.PanelOptionNamesString);
		LayoutOptionNamesBoolean =(String[]) ArrayUtils.addAll(LayoutOptionNamesBoolean, PanelTag.PanelOptionNamesBoolean);
		LayoutOptionNamesNumber =(String[]) ArrayUtils.addAll(LayoutOptionNamesNumber, PanelTag.PanelOptionNamesNumber);
		LayoutEventNames = (String[]) ArrayUtils.addAll(LayoutEventNames, PanelTag.PanelEventNames);
	}

	public LayoutTag() {
		this.setTagClass("easyui-layout");
		this.setOptionNamesString(LayoutOptionNamesString);
		this.setOptionNamesBoolean(LayoutOptionNamesBoolean);
		this.setOptionNamesNumber(LayoutOptionNamesNumber);
		this.setEventNames(LayoutEventNames);
	}
	
	
}
