package com.loit.core.web.tag.easyui;

public class AccordionTag extends BaseEasyUIDivTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static String[] AccordionOptionNamesString = { "title",
			"iconCls", "cls", "headerCls", "bodyCls", "content", "tools",
			"href", "loadingMessage", "extractor" };
	protected static String[] AccordionOptionNamesBoolean = { "fit", "border",
			"doSize", "closable", "closed", "noheader", "collapsible",
			"minimizable", "maximizable", "collapsed", "minimized",
			"maximized", "cache", "animate", "selected" };
	protected static String[] AccordionOptionNamesNumber = { "width", "height",
			"left", "top", };

	protected static String[] AccordionEventNames = { "onLoad", "onBeforeOpen",
			"onOpen", "onBeforeClose", "onClose", "onBeforeDestroy",
			"onDestroy", "onBeforeCollapse", "onCollapse", "onBeforeExpand",
			"onExpand", "onResize", "onMove", "onMaximize", "onRestore",
			"onMinimize", "onSelect", "onAdd", "onBeforeRemove", "onRemove" };

	public AccordionTag() {
		this.setTagClass("easyui-accordion");
		this.setOptionNamesString(AccordionOptionNamesString);
		this.setOptionNamesBoolean(AccordionOptionNamesBoolean);
		this.setOptionNamesNumber(AccordionOptionNamesNumber);
		this.setEventNames(AccordionEventNames);
	}

}
