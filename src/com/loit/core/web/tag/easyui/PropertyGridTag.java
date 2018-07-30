package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class PropertyGridTag extends BaseEasyUITag {

	private static final long serialVersionUID = 1L;

	protected static String[] PropertyGridOptionNamesString = { "groupField", "resizeHandle", "method", "idField", "url", "loadMsg", "pagePosition", "sortName",
			"sortOrder" };
	protected static String[] PropertyGridOptionNamesBoolean = { "showGroup","pagination", "rownumbers", "singleSelect", "autoRowHeight", "striped", "nowrap",
			"checkOnSelect", "selectOnCheck", "remoteSort", "showHeader", "showFooter" };
	protected static String[] PropertyGridOptionNamesNumber = { "width", "height", "left", "top", "pageNumber", "pageSize","scrollbarSize" };

	protected static String[] PropertyGridEventNames = { "groupFormatter", "toolbar","columns", "frozenColumns", "fitColumns", "data", "rowStyler", "queryParams", "pageList",
			"onBeforeCollapse", "onCollapse", "loader", "loadFilter", "editors", "view", "onLoadSuccess", "onLoadError", "onBeforeLoad",
			"onClickRow", "onDblClickRow", "onClickCell", "onDblClickCell", "onSortColumn", "onResizeColumn", "onSelect", "onUnselect",
			"onSelectAll", "onUnselectAll", "onCheck", "onUncheck", "onCheckAll", "onUncheckAll", "onBeforeEdit", "onAfterEdit", "onCancelEdit",
			"onHeaderContextMenu", "onRowContextMenu" };

	static{
		PropertyGridOptionNamesString = (String[]) ArrayUtils.addAll(PropertyGridOptionNamesString, DataGridTag.DataGridOptionNamesString);
		PropertyGridOptionNamesBoolean =(String[]) ArrayUtils.addAll(PropertyGridOptionNamesBoolean, DataGridTag.DataGridOptionNamesBoolean);
		PropertyGridOptionNamesNumber =(String[]) ArrayUtils.addAll(PropertyGridOptionNamesNumber, DataGridTag.DataGridOptionNamesNumber);
		PropertyGridEventNames = (String[]) ArrayUtils.addAll(PropertyGridEventNames, DataGridTag.DataGridEventNames);
	}
	
	public PropertyGridTag() {
		super.setTagClass("easyui-propertygrid");
		super.setTagName("table");
		this.setOptionNamesString(PropertyGridOptionNamesString);
		this.setOptionNamesBoolean(PropertyGridOptionNamesBoolean);
		this.setOptionNamesNumber(PropertyGridOptionNamesNumber);
		this.setEventNames(PropertyGridEventNames);
	}


	
}
