package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class LayoutCenterTag extends BaseEasyUIDivTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String[] LayoutCenterOptionNamesString = {"title","region","href","iconCls"};
	private static String[] LayoutCenterOptionNamesBoolean = {"border","split","collapsible","disabled"};
	private static String[] LayoutCenterOptionNamesNumber = {"minWidth","minHeight","maxWidth","maxHeight"};
	private static String[] LayoutCenterEventNames = {};
	
	static{
		LayoutCenterOptionNamesString = (String[]) ArrayUtils.addAll(LayoutCenterOptionNamesString, PanelTag.PanelOptionNamesString);
		LayoutCenterOptionNamesBoolean =(String[]) ArrayUtils.addAll(LayoutCenterOptionNamesBoolean, PanelTag.PanelOptionNamesBoolean);
		LayoutCenterOptionNamesNumber =(String[]) ArrayUtils.addAll(LayoutCenterOptionNamesNumber, PanelTag.PanelOptionNamesNumber);
		LayoutCenterEventNames = (String[]) ArrayUtils.addAll(LayoutCenterEventNames, PanelTag.PanelEventNames);
	}
	
	private String 	minWidth	;
	private String 	minHeight	;
	private String 	maxWidth	;
	private String 	maxHeight	;
	private String 	region	;
	private String 	split	;
	
	public LayoutCenterTag() {
		this.setRegion("center");
		this.setOptionNamesString(LayoutCenterOptionNamesString);
		this.setOptionNamesBoolean(LayoutCenterOptionNamesBoolean);
		this.setOptionNamesNumber(LayoutCenterOptionNamesNumber);
		this.setEventNames(LayoutCenterEventNames);
	}
	
	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}

	public String getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}
}
