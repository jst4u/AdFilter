package com.loit.core.web.tag.easyui;

public class PanelTag extends BaseEasyUIDivTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static String[] PanelOptionNamesString = { "title", "iconCls", "cls", "headerCls", "bodyCls", "content", "tools", "href", "loadingMessage",
			"extractor" };
	protected static String[] PanelOptionNamesBoolean = { "fit", "border", "doSize", "closable", "closed", "noheader", "collapsible", "minimizable",
			"maximizable", "collapsed", "minimized", "maximized", "cache" };
	protected static String[] PanelOptionNamesNumber = { "width", "height", "left", "top", };

	protected static String[] PanelEventNames = { "onLoad", "onBeforeOpen", "onOpen", "onBeforeClose", "onClose", "onBeforeDestroy", "onDestroy",
			"onBeforeCollapse", "onCollapse", "onBeforeExpand", "onExpand", "onResize", "onMove", "onMaximize", "onRestore", "onMinimize" };
	
	public PanelTag(){
		this.setTagClass("easyui-panel");
		this.setOptionNamesString(PanelOptionNamesString);
		this.setOptionNamesBoolean(PanelOptionNamesBoolean);
		this.setOptionNamesNumber(PanelOptionNamesNumber);
		this.setEventNames(PanelEventNames);
	}

}
