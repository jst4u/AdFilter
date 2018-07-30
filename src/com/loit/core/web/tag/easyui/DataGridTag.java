package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ArrayUtils;

public class DataGridTag extends BaseEasyUITag {

	private static final long serialVersionUID = 1L;

	protected static String[] DataGridOptionNamesString = { "resizeHandle", "method", "url", "loadMsg", "pagePosition", "sortName",
			"sortOrder" };
	protected static String[] DataGridOptionNamesBoolean = { "pagination", "rownumbers", "singleSelect", "autoRowHeight", "striped", "nowrap",
			"checkOnSelect", "selectOnCheck", "remoteSort", "showHeader", "showFooter" };
	protected static String[] DataGridOptionNamesNumber = { "width", "height", "left", "top", "pageNumber", "pageSize","scrollbarSize" };

	protected static String[] DataGridEventNames = {  "toolbar","columns", "frozenColumns", "fitColumns", "data", "rowStyler", "queryParams", "pageList",
			"onBeforeCollapse", "onCollapse", "loader", "loadFilter", "editors", "view", "onLoadSuccess", "onLoadError", "onBeforeLoad",
			"onClickRow", "onDblClickRow", "onClickCell", "onDblClickCell", "onSortColumn", "onResizeColumn", "onSelect", "onUnselect",
			"onSelectAll", "onUnselectAll", "onCheck", "onUncheck", "onCheckAll", "onUncheckAll", "onBeforeEdit", "onAfterEdit", "onCancelEdit",
			"onHeaderContextMenu", "onRowContextMenu" };

	static{
		DataGridOptionNamesString = (String[]) ArrayUtils.addAll(DataGridOptionNamesString, PanelTag.PanelOptionNamesString);
		DataGridOptionNamesBoolean =(String[]) ArrayUtils.addAll(DataGridOptionNamesBoolean, PanelTag.PanelOptionNamesBoolean);
		DataGridOptionNamesNumber =(String[]) ArrayUtils.addAll(DataGridOptionNamesNumber, PanelTag.PanelOptionNamesNumber);
		DataGridEventNames = (String[]) ArrayUtils.addAll(DataGridEventNames, PanelTag.PanelEventNames);
	}
	
	public DataGridTag() {
		super.setTagClass("easyui-datagrid");
		super.setTagName("table");
		this.setOptionNamesString(DataGridOptionNamesString);
		this.setOptionNamesBoolean(DataGridOptionNamesBoolean);
		this.setOptionNamesNumber(DataGridOptionNamesNumber);
		this.setEventNames(DataGridEventNames);
	}


	public int doEndTag()throws JspException{
//		TODO
		StringBuffer sb =  new StringBuffer();
//		输出冻结列
		if(null!=getFrozenColumns()){
			sb.append("<thead data-options=\"frozen:true\"><tr>");
			sb.append(getFrozenColumns());
			sb.append("</tr></thead>");
			
		}
		if(null!=getColumns()){
			sb.append("<thead><tr>");
			sb.append(getColumns());
			sb.append("</tr></thead>");
		}
		outHtml(sb);
//		输出非冻结列
		return super.doEndTag();
	}
	public void release(){
		super.release();
		setFrozenColumns(null);
		setColumns(null);
	}
}
