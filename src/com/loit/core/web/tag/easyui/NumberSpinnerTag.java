package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class NumberSpinnerTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] NumberSpinnerOptionNamesString = {};
	protected static String[] NumberSpinnerOptionNamesBoolean = {"editable","disabled"};
	protected static String[] NumberSpinnerOptionNamesNumber = {"width","height","increment","min", "max"};
	
	protected static String[] NumberSpinnerEventNames = { "spin", "onSpinUp", "onSpinDown"};
	
	static{
		NumberSpinnerOptionNamesString = (String[]) ArrayUtils.addAll(NumberSpinnerOptionNamesString, NumberBoxTag.NumberBoxOptionNamesString);
		NumberSpinnerOptionNamesBoolean =(String[]) ArrayUtils.addAll(NumberSpinnerOptionNamesBoolean, NumberBoxTag.NumberBoxOptionNamesBoolean);
		NumberSpinnerOptionNamesNumber =(String[]) ArrayUtils.addAll(NumberSpinnerOptionNamesNumber, NumberBoxTag.NumberBoxOptionNamesNumber);
		NumberSpinnerEventNames = (String[]) ArrayUtils.addAll(NumberSpinnerEventNames, NumberBoxTag.NumberBoxEventNames);
	}
	
	public NumberSpinnerTag(){
		this.setTagClass("easyui-numberspinner");
		this.setTagName("input");
		this.setOptionNamesString(NumberSpinnerOptionNamesString);
		this.setOptionNamesBoolean(NumberSpinnerOptionNamesBoolean);
		this.setOptionNamesNumber(NumberSpinnerOptionNamesNumber);
		this.setEventNames(NumberSpinnerEventNames);
	}
	
	

}
