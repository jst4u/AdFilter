package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class ComboBoxTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;

	protected static String[] ComboBoxOptionNamesString = { "valueField", "textField", "mode", "url", "method",  "filter", "formatter", "loader", "loadFilter"
		,  "codeType", "codeFilter", "panelWidth", "panelHeight", };
	protected static String[] ComboBoxOptionNamesBoolean = { };
	protected static String[] ComboBoxOptionNamesNumber = { };

	protected static String[] ComboBoxEventNames = { "data","keyHandler","onBeforeLoad", "onLoadSuccess", "onLoadError", "onSelect", "onUnselect"};

	static{
		ComboBoxOptionNamesString = (String[]) ArrayUtils.addAll(ComboBoxOptionNamesString, ComboTag.ComboOptionNamesString);
		ComboBoxOptionNamesBoolean =(String[]) ArrayUtils.addAll(ComboBoxOptionNamesBoolean, ComboTag.ComboOptionNamesBoolean);
		ComboBoxOptionNamesNumber =(String[]) ArrayUtils.addAll(ComboBoxOptionNamesNumber, ComboTag.ComboOptionNamesNumber);
		ComboBoxEventNames = (String[]) ArrayUtils.addAll(ComboBoxEventNames, ComboTag.ComboEventNames);
	}
	
	public ComboBoxTag() {
		this.setTagClass("easyui-combobox");
		this.setTagName("input");
		this.setOptionNamesString(ComboBoxOptionNamesString);
		this.setOptionNamesBoolean(ComboBoxOptionNamesBoolean);
		this.setOptionNamesNumber(ComboBoxOptionNamesNumber);
		this.setEventNames(ComboBoxEventNames);
	}
}
