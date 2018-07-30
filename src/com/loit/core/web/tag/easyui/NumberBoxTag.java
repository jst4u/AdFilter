package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class NumberBoxTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;

	protected static String[] NumberBoxOptionNamesString = { "decimalSeparator", "groupSeparator", "prefix", "suffix"};
	protected static String[] NumberBoxOptionNamesBoolean = { "disabled" ,"required"};
	protected static String[] NumberBoxOptionNamesNumber = {  "min", "max", "precision" };

	protected static String[] NumberBoxEventNames = { "filter", "formatter", "parser", "onChange"};

	static{
		NumberBoxOptionNamesString = (String[]) ArrayUtils.addAll(NumberBoxOptionNamesString, ValidateBoxTag.ValidateBoxOptionNamesString);
		NumberBoxOptionNamesBoolean =(String[]) ArrayUtils.addAll(NumberBoxOptionNamesBoolean, ValidateBoxTag.ValidateBoxOptionNamesBoolean);
		NumberBoxOptionNamesNumber =(String[]) ArrayUtils.addAll(NumberBoxOptionNamesNumber, ValidateBoxTag.ValidateBoxOptionNamesNumber);
		NumberBoxEventNames = (String[]) ArrayUtils.addAll(NumberBoxEventNames, ValidateBoxTag.ValidateBoxEventNames);
	}
	
	public NumberBoxTag() {
		this.setTagClass("easyui-numberbox");
		this.setTagName("input");
		this.setOptionNamesString(NumberBoxOptionNamesString);
		this.setOptionNamesBoolean(NumberBoxOptionNamesBoolean);
		this.setOptionNamesNumber(NumberBoxOptionNamesNumber);
		this.setEventNames(NumberBoxEventNames);
	}
	
}
