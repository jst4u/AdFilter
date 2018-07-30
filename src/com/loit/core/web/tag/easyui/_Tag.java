package com.loit.core.web.tag.easyui;

public class _Tag extends BaseEasyUITag {
	/**
	 * Dependencies
	 * panel
	 * resizable
	 */
	private static final long serialVersionUID = 1L;

	private static String[] PanelOptionNamesString = {};
	private static String[] PanelOptionNamesBoolean = {"fit"};
	private static String[] PanelOptionNamesNumber = {};

	private static String[] PanelEventNames = {};

	public _Tag() {
		this.setTagClass("easyui-pluginName");
		
		this.setOptionNamesString(PanelOptionNamesString);
		this.setOptionNamesBoolean(PanelOptionNamesBoolean);
		this.setOptionNamesNumber(PanelOptionNamesNumber);
		
		this.setEventNames(PanelEventNames);
	}
}
