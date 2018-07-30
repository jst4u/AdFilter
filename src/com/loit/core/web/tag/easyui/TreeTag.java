package com.loit.core.web.tag.easyui;

public class TreeTag extends BaseEasyUITag {
	/**
	 * Dependencies panel resizable
	 */
	private static final long serialVersionUID = 1L;

	protected static String[] TreeOptionNamesString = { "url", "idField", "textField", "pidField" };
	protected static String[] TreeOptionNamesBoolean = { "animate", "checkbox", "cascadeCheck", "onlyLeafCheck", "lines", "dnd", };
	protected static String[] TreeOptionNamesNumber = { "openLevel" };

	protected static String[] TreeEventNames = { "data", "formatter", "loader", "loadFilter", "onClick", "onDblClick", "onBeforeLoad", "onLoadSuccess",
			"onLoadError", "onBeforeExpand", "onExpand", "onBeforeCollapse", "onCollapse", "onBeforeCheck", "onCheck", "onBeforeSelect",
			"onSelect", "onContextMenu", "onBeforeDrag", "onStartDrag", "onStopDrag", "onDragEnter", "onDragOver", "onDragLeave", "onBeforeDrop",
			"onDrop", "onBeforeEdit", "onAfterEdit", "onCancelEdit"};

	public TreeTag() {
		this.setTagClass("easyui-tree");
		this.setTagName("ul");
		this.setOptionNamesString(TreeOptionNamesString);
		this.setOptionNamesBoolean(TreeOptionNamesBoolean);
		this.setOptionNamesNumber(TreeOptionNamesNumber);

		this.setEventNames(TreeEventNames);
	}
}
