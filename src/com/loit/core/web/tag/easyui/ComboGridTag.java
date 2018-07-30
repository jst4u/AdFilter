package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class ComboGridTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;

	private static String[] ComboGridOptionNamesString = { "loadMsg", "idField", "textField", "mode", "filter",
		"fitColumns","url"};
	private static String[] ComboGridOptionNamesBoolean = { };
	private static String[] ComboGridOptionNamesNumber = { };

	private static String[] ComboGridEventNames = {"columns" };

	static{
		ComboGridOptionNamesString = (String[]) ArrayUtils.addAll(ComboGridOptionNamesString, ComboTag.ComboOptionNamesString);
		ComboGridOptionNamesBoolean =(String[]) ArrayUtils.addAll(ComboGridOptionNamesBoolean, ComboTag.ComboOptionNamesBoolean);
		ComboGridOptionNamesNumber =(String[]) ArrayUtils.addAll(ComboGridOptionNamesNumber, ComboTag.ComboOptionNamesNumber);
		ComboGridEventNames = (String[]) ArrayUtils.addAll(ComboGridEventNames, ComboTag.ComboEventNames);
	}
	
	public ComboGridTag() {
		this.setTagClass("easyui-combogrid");
		this.setTagName("select");
		this.setOptionNamesString(ComboGridOptionNamesString);
		this.setOptionNamesBoolean(ComboGridOptionNamesBoolean);
		this.setOptionNamesNumber(ComboGridOptionNamesNumber);
		this.setEventNames(ComboGridEventNames);
	}

}
